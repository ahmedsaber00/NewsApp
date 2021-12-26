package com.saber.sabernews.presentation.features.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saber.sabernews.domain.base.result.data
import com.saber.sabernews.domain.feature.news.interactor.GetAllNews
import com.saber.sabernews.domain.feature.news.model.News
import com.saber.sabernews.presentation.features.news.mapper.NewsResponseViewMapper
import com.saber.sabernews.presentation.features.news.mapper.NewsViewMapper
import com.saber.sabernews.presentation.features.news.model.NewsResponseView
import com.saber.sabernews.presentation.features.news.model.NewsView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 20

const val STATE_KEY_RESERVATIONS_PAGE = "news.state.page.key"
const val STATE_KEY_RESERVATIONS_LIST_POSITION = "news.state.query.list_position"

@HiltViewModel
class NewsViewModle @Inject constructor(
    private val mapperNewsResponse: NewsResponseViewMapper,
    private val mapperNews: NewsViewMapper,
    private val getAllNews: GetAllNews,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val loading = mutableStateOf(false)
    val pageNews = mutableStateOf(1)
    var newsListScrollPosition = 0
    val newsResponses: MutableState<NewsResponseView?> = mutableStateOf(null)
    val news: MutableState<List<NewsView>?> = mutableStateOf(arrayListOf())
    val searchText = mutableStateOf("")

    fun onChangeNewsScrollPosition(position: Int) = setNewsScrollPosition(position = position)

    private fun incrementNewsPage() = setNewsPage(pageNews.value + 1)

    private fun setNewsScrollPosition(position: Int) {
        newsListScrollPosition = position
        savedStateHandle.set(STATE_KEY_RESERVATIONS_LIST_POSITION, position)
    }

    init {
        savedStateHandle.get<Int>(STATE_KEY_RESERVATIONS_PAGE)?.let { p -> setNewsPage(p) }
        savedStateHandle.get<Int>(STATE_KEY_RESERVATIONS_LIST_POSITION)
            ?.let { p -> setNewsScrollPosition(p) }
        // get news
        if (newsListScrollPosition != 0) onTriggerNewsEvent(NewsListEvent.RestoreStateEvent)
    }

    fun onTriggerNewsEvent(event: NewsListEvent, search: String = "") {
        viewModelScope.launch {
            try {
                when (event) {
                    is NewsListEvent.FirstPageEvent -> newNewsSearch(search)
                    is NewsListEvent.NextPageEvent -> nextNewsPage()
                    is NewsListEvent.RestoreStateEvent -> restoreNewsState()
                }
            } catch (e: Exception) {
                Log.e("launchJob: Exception: ${e}, ${e.cause}", "")
                e.printStackTrace()
            } finally {
                Log.d("launchJob: finally called.", "")
            }
        }
    }

    private fun setNewsPage(page: Int) {
        this.pageNews.value = page
        savedStateHandle.set(STATE_KEY_RESERVATIONS_PAGE, page)
    }

    private suspend fun newNewsSearch(search: String) {
        searchText.value = search
        getAllNews(
            GetAllNews.Params(pageNumber = 1, PAGE_SIZE, search)
        )
            .onStart { loading.value = true }
            .onCompletion { loading.value = false }
            .catch { loading.value = false }
            .collect {
                it.data?.let { item ->
                    this.newsResponses.value = mapperNewsResponse.mapToView(item)
                    news.value = newsResponses?.value?.articles
                }
            }
    }

    private suspend fun nextNewsPage() {
        // prevent duplicate event due to recompose happening to quickly
        if ((newsListScrollPosition + 1) >= (pageNews.value * PAGE_SIZE)) {
            loading.value = true
            incrementNewsPage()
            Log.d("nextPage: triggered: ${pageNews.value}", "")
            // just to show pagination, api is fast
            if (pageNews.value > 0) {
                getAllNews(
                    GetAllNews.Params(
                        pageNumber = pageNews.value,
                        PAGE_SIZE,
                        searchText.value
                    )
                )
                    .onStart { loading.value = true }
                    .onCompletion { loading.value = false }
                    .catch { loading.value = false }
                    .collect {
                        it.data?.articles.let { list ->
                            list?.map { item -> mapperNews.mapToView(item) }
                                ?.let { it1 -> appendNews(it1) }
                        }
                    }
            }
            loading.value = false
        }
    }

    private suspend fun restoreNewsState() {
        loading.value = true
        val results: MutableList<News> = mutableListOf()
        for (p in 0..pageNews.value) {
            getAllNews(GetAllNews.Params(pageNumber = p, PAGE_SIZE, searchText.value))
                .onStart { loading.value = true }
                .onCompletion { loading.value = false }
                .catch { loading.value = false }
                .collect {
                    it.data?.articles?.let { list -> results.addAll((list)) }
                    if (p == pageNews.value) {
                        // done
                        news.value = results.map { item -> mapperNews.mapToView(item) }
                    }
                }
        }
        loading.value = false
    }

    /**
     * Append new news to the current list of news
     */
    private fun appendNews(news: List<NewsView>) {
        val current = ArrayList(this.news.value)
        current.addAll(news)
        this.news.value = current
    }

}
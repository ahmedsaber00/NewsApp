package com.saber.sabernews.domain.feature.news.interactor

import com.saber.sabernews.domain.base.interactor.UseCase
import com.saber.sabernews.domain.base.result.AResult
import com.saber.sabernews.domain.feature.news.NewsRepository
import com.saber.sabernews.domain.feature.news.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNews @Inject constructor(
    private val repository: NewsRepository,
) : UseCase<Flow<AResult<NewsResponse>>, GetAllNews.Params>() {

    override fun execute(params: Params): Flow<AResult<NewsResponse>> = flow {
        try {
            emit(
                AResult.Success(
                    repository.getAllNews(
                        params.pageNumber,
                        params.pageSize,
                        params.searchQuery
                    )
                )
            )
        } catch (e: Exception) {
            emit(AResult.Error(e))
        }
    }

    data class Params(val pageNumber: Int, val pageSize: Int, val searchQuery: String)

}
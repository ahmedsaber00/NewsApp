package com.saber.sabernews.presentation.features.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.saber.sabernews.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private val viewModel: NewsViewModle by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val scaffoldState = rememberScaffoldState()

                val loading = viewModel.loading.value

                val pageNews = viewModel.pageNews.value
                val news = viewModel.news.value
                //val isRefreshingNews = viewModel.isRefreshingNews.collectAsState()

                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = { scaffoldState.snackbarHostState }) {
                    NewsScreen(
                        loading = loading,
                        pageNews = pageNews,
                        news = news ?: arrayListOf(),
                        onChangeNewsScrollPosition = viewModel::onChangeNewsScrollPosition,
                        onTriggerEvent = { handleInteractionEvents(it, findNavController()) },
                        onImeAction = {
                            viewModel.onTriggerNewsEvent(
                                NewsListEvent.FirstPageEvent,
                                it
                            )
                        }
                    )
                }
            }
        }
    }


    private fun handleInteractionEvents(
        event: NewsListEvent,
        findNavController: NavController
    ) {
        when (event) {
            is NewsListEvent.OpenNewsDetails -> {
                val bundle = Bundle()
                bundle.putString(PARAM_URL, event.news.url)
                findNavController.navigate(R.id.viewNewsDetails, bundle)

            }
            else -> viewModel.onTriggerNewsEvent(event)
        }
    }
}
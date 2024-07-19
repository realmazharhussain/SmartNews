package io.github.realmazharhussain.smartnews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.realmazharhussain.smartnews.network.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    @Suppress("MemberVisibilityCanBePrivate")
    val query = MutableStateFlow("technology")
    val everything = query.debounce(1000).flatMapLatest {
        Pager(
            PagingConfig(20)
        ) {
            NewsPagingSource(repository, it)
        }.flow.map { pagingData ->
            pagingData.filter { article ->
                article.title != "[Removed]"
            }
        }
    }.cachedIn(viewModelScope)
}

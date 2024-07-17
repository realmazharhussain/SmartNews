package io.github.realmazharhussain.smartnews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.realmazharhussain.smartnews.data.database.CacheDatabase
import io.github.realmazharhussain.smartnews.data.database.repository.NewsRepositoryLocal
import io.github.realmazharhussain.smartnews.data.network.repository.NewsRepositoryRemote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NewsViewModel @Inject constructor(
    private val cacheDb: CacheDatabase,
    private val remoteRepository: NewsRepositoryRemote,
    private val localRepository: NewsRepositoryLocal,
) : ViewModel() {
    @Suppress("MemberVisibilityCanBePrivate")
    val query = MutableStateFlow("technology")

    @OptIn(ExperimentalPagingApi::class)
    val everything = query.debounce(1000).flatMapLatest { query ->
        Pager(
            config = PagingConfig(20),
            remoteMediator = NewsRemoteMediator(query, remoteRepository, localRepository)
        ) {
            localRepository.pagingSource()
        }.flow.map { pagingData ->
            pagingData.filter { article ->
                article.title != "[Removed]" && !article.url.startsWith("https://consent.yahoo.com/")
            }.map { it.toArticle() }
        }
    }.cachedIn(viewModelScope)
}

package io.github.realmazharhussain.smartnews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.realmazharhussain.smartnews.network.repository.NewsRepository
import io.github.realmazharhussain.smartnews.util.flowState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    @Suppress("MemberVisibilityCanBePrivate")
    val query = MutableStateFlow("technology")
    val everything = query.debounce(1000).flatMapLatest { fetchEverything(it) }
    private fun fetchEverything(query: String, pageNo: Int = 1) = flowState(viewModelScope, SharingStarted.Lazily) { repository.everything(query, pageNo) }
}

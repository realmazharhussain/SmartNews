package io.github.realmazharhussain.smartnews.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.realmazharhussain.smartnews.data.database.NewsRepositoryLocal
import io.github.realmazharhussain.smartnews.data.network.repository.SummaryRepository
import io.github.realmazharhussain.smartnews.util.flowState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsRepositoryLocal: NewsRepositoryLocal,
    private val summaryRepository: SummaryRepository
) : ViewModel() {
    fun getDetails(url: String) = flowState(viewModelScope) {
        newsRepositoryLocal.get(url) ?: throw IllegalStateException("Article with URL \"$url\" could not be found")
    }

    fun getSummary(url: String) = flowState(viewModelScope) { summaryRepository.summarize(url) }
}

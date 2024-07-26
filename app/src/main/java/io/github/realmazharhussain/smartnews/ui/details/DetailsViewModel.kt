package io.github.realmazharhussain.smartnews.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.realmazharhussain.smartnews.data.database.NewsRepositoryLocal
import io.github.realmazharhussain.smartnews.data.database.SummaryRepositoryLocal
import io.github.realmazharhussain.smartnews.data.network.repository.SummaryRepositoryRemote
import io.github.realmazharhussain.smartnews.util.flowState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsRepositoryLocal: NewsRepositoryLocal,
    private val summaryRepositoryLocal: SummaryRepositoryLocal,
    private val summaryRepositoryRemote: SummaryRepositoryRemote
) : ViewModel() {

    fun getDetails(id: Int) = flowState(viewModelScope) {
        newsRepositoryLocal.get(id)?.toArticle() ?: throw IllegalStateException("Article with id \"$id\" could not be found")
    }

    fun getSummary(url: String) = flowState(viewModelScope) {
        summaryRepositoryLocal.find(url)?.summary
            ?: summaryRepositoryRemote.summarize(url).also {
                summaryRepositoryLocal.add(url, it)
            }
    }
}

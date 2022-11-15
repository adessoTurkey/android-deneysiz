package com.deneyehayir.deneysiz.scene.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchSearchResultUseCase
import com.deneyehayir.deneysiz.internal.extension.toErrorContentUiModel
import com.deneyehayir.deneysiz.scene.search.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchSearchResultUseCase: FetchSearchResultUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState.Initial)
    val uiState: StateFlow<SearchUiState> get() = _uiState

    var queryFlowText = MutableStateFlow("")
        private set


    fun handleUiEvents(event: SearchDetailUiEvents) {
        when (event) {
            is SearchDetailUiEvents.QueryTextChange -> {
                queryFlowText.value = event.query
                makeSearch()

            }
            SearchDetailUiEvents.CancelButtonClick -> {
                _uiState.update {
                    it.cancelActions()
                }
                queryFlowText.value = ""
            }
            SearchDetailUiEvents.ErrorCloseClick -> {
                _uiState.update {
                    it.hideError()
                }
            }
            SearchDetailUiEvents.ErrorRetryClick -> {
                _uiState.update {
                    it.hideError()
                }
                makeSearch()
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun makeSearch() {
        viewModelScope.launch {
            queryFlowText.debounce(700).filter { str ->
                if (str.isEmpty() || str.length <= 2) {
                    _uiState.update {
                        it.removeList()
                    }
                    return@filter false
                } else return@filter true
            }.distinctUntilChanged().flatMapLatest { str ->
                _uiState.update {
                    it.showLoading()
                }
                fetchSearchResultUseCase.execute(
                    FetchSearchResultUseCase.Params(
                        str
                    )
                )
            }.catch { err ->
                _uiState.update {
                    it.showError(err.toErrorContentUiModel())
                }
            }.collect { result ->
                if (result.shouldShowError) {
                    _uiState.update {
                        it.showBrandNotFoundError()
                    }
                } else {
                    _uiState.update { state ->
                        state.hideBrandNotFoundError()
                    }
                    _uiState.update { state ->
                        state.updateSearchResult(result.toUiModel().items)
                    }
                }
            }
        }
    }
}

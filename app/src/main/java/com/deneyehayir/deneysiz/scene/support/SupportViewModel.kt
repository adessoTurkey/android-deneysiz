package com.deneyehayir.deneysiz.scene.support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchSupportDataUseCase
import com.deneyehayir.deneysiz.scene.support.model.SupportActionUiModel
import com.deneyehayir.deneysiz.scene.support.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportViewModel @Inject constructor(
    private val supportDataUseCase: FetchSupportDataUseCase
) : ViewModel() {
    private var _currentViewState: SupportViewState = SupportViewState.Initial
    private val _viewState = MutableStateFlow(_currentViewState)
    val viewState: StateFlow<SupportViewState> = _viewState

    val updateExpandedState: (supportAction: SupportActionUiModel) -> Unit = { supportAction ->
        _currentViewState = _currentViewState.updateExpandableState(supportAction)
        _viewState.value = _currentViewState
    }

    init {
        fetchSupportData()
    }

    private fun fetchSupportData() = viewModelScope.launch {
        supportDataUseCase(Unit)
            .onSuccess { supportData ->
                _currentViewState =
                    _currentViewState.updateSupportViewState(supportData.toUiModel())
                _viewState.value = _currentViewState
            }
            .onFailure {
                _currentViewState = _currentViewState.setError()
                _viewState.value = _currentViewState
            }
    }
}

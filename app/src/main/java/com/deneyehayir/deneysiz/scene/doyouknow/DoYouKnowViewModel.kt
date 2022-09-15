package com.deneyehayir.deneysiz.scene.doyouknow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchDoYouKnowDataUseCase
import com.deneyehayir.deneysiz.scene.doyouknow.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DoYouKnowViewModel @Inject constructor(
    private val doYouKnowDataUseCase: FetchDoYouKnowDataUseCase
) : ViewModel() {
    private val _viewState: DoYouKnowViewState = DoYouKnowViewState.Initial
    val viewState: StateFlow<DoYouKnowViewState> = flow {
        doYouKnowDataUseCase(Unit)
            .onSuccess { doYouKnowData ->
                emit(_viewState.updateDoYouKnowUiModel(doYouKnowData.toUiModel()))
            }
            .onFailure {
                emit(_viewState.setError())
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _viewState
    )
}

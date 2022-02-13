package com.deneyehayir.deneysiz.scene.whoweare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchWhoWeAreDataUseCase
import com.deneyehayir.deneysiz.scene.whoweare.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WhoWeAreViewModel @Inject constructor(
    private val whoWeAreDataUseCase: FetchWhoWeAreDataUseCase
) : ViewModel() {
    private val _viewState: WhoWeAreViewState = WhoWeAreViewState.Initial
    val viewState: StateFlow<WhoWeAreViewState> = flow {
        whoWeAreDataUseCase(Unit)
            .onSuccess { whoWeAreData ->
                emit(_viewState.updateWhoWeAreViewState(whoWeAreData.toUiModel()))
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

package com.deneyehayir.deneysiz.scene.doyouknowcontent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchDoYouKnowContentByIdUseCase
import com.deneyehayir.deneysiz.scene.doyouknowcontent.model.toUiModel
import com.deneyehayir.deneysiz.scene.navDoYouKnowContentId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DoYouKnowContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val doYouKnowContentByIdUseCase: FetchDoYouKnowContentByIdUseCase
) : ViewModel() {
    private val id = savedStateHandle.get<Int>(navDoYouKnowContentId) ?: -1
    private val _viewState: DoYouKnowContentViewState = DoYouKnowContentViewState.Initial
    val viewState: StateFlow<DoYouKnowContentViewState> = flow {
        doYouKnowContentByIdUseCase(
            params = FetchDoYouKnowContentByIdUseCase.Params(
                id = id
            )
        )
            .onSuccess { content ->
                emit(_viewState.updateContentUiModel(content.toUiModel()))
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

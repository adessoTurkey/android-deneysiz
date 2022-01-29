package com.deneyehayir.deneysiz.scene.faq

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchFaqByIdUseCase
import com.deneyehayir.deneysiz.scene.faq.model.toUiModel
import com.deneyehayir.deneysiz.scene.navFaqId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FaqViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val faqByIdUseCase: FetchFaqByIdUseCase
) : ViewModel() {
    private val id = savedStateHandle.get<Int>(navFaqId) ?: -1
    private val _viewState: FaqViewState = FaqViewState.Initial
    val viewState: StateFlow<FaqViewState> = flow {
        faqByIdUseCase(
            params = FetchFaqByIdUseCase.Params(
                id = id
            )
        )
            .onSuccess { faq ->
                emit(_viewState.updateDoYouKnowUiModel(faq.toUiModel()))
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

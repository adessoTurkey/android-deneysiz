package com.deneyehayir.deneysiz.scene.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchCategoryDataUseCase
import com.deneyehayir.deneysiz.scene.discover.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val categoryDataUseCase: FetchCategoryDataUseCase
) : ViewModel() {
    private val initialViewState: DiscoverViewState = DiscoverViewState.Initial
    val discoverViewState: StateFlow<DiscoverViewState> = flow {
        categoryDataUseCase(Unit)
            .onSuccess { categoryData ->
                emit(initialViewState.updateUiModel(categoryData.toUiModel()))
            }
            .onFailure {
                // TODO map throwable to appropriate error message
                emit(initialViewState.setError())
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = initialViewState
    )
}

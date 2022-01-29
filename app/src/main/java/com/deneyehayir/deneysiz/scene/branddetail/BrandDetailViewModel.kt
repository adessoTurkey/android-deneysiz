package com.deneyehayir.deneysiz.scene.branddetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchBrandDetailUseCase
import com.deneyehayir.deneysiz.scene.branddetail.model.toUiModel
import com.deneyehayir.deneysiz.scene.navBrandDetailBrandId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val brandDetail: FetchBrandDetailUseCase
) : ViewModel() {
    private val brandId = savedStateHandle.get<Int>(navBrandDetailBrandId) ?: -1
    private var viewState: BrandDetailViewState = BrandDetailViewState.Initial
    private val _brandDetailViewState = MutableStateFlow(viewState)
    val brandDetailViewState: StateFlow<BrandDetailViewState> = _brandDetailViewState

    /*flow {
    brandDetail(params = FetchBrandDetailUseCase.Params(brandId = brandId))
        .onSuccess { brandDetail ->
            viewState = viewState.setBrandDetailData(brandDetail.toUiModel())
            emit(viewState)
        }
        .onFailure {  }
}.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = viewState
)*/

    val onScoreDetail: (() -> Unit) = {
        viewState = viewState.changeScoreDetailDialogVisibility()
        _brandDetailViewState.value = viewState
    }

    init {
        fetchBrandDetail()
    }

    private fun fetchBrandDetail() = viewModelScope.launch {
        brandDetail(params = FetchBrandDetailUseCase.Params(brandId = brandId))
            .onSuccess { brandDetail ->
                viewState = viewState.setBrandDetailData(brandDetail.toUiModel())
                _brandDetailViewState.value = viewState
            }
            .onFailure { }
    }
}

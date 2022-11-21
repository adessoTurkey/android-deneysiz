package com.deneyehayir.deneysiz.scene.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchAllFollowingBrandsUseCase
import com.deneyehayir.deneysiz.domain.usecase.RemoveBrandFromFollowingUseCase
import com.deneyehayir.deneysiz.scene.categorydetail.model.CategoryDetailItemUiModel
import com.deneyehayir.deneysiz.scene.categorydetail.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val fetchAllFollowingBrandsUseCase: FetchAllFollowingBrandsUseCase,
    private val removeBrandFromFollowingUseCase: RemoveBrandFromFollowingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FollowingUiState>(FollowingUiState.Loading)
    val uiState: StateFlow<FollowingUiState> get() = _uiState

    init {
        initFollowingItems()
    }

    private fun initFollowingItems() {
        viewModelScope.launch {
            fetchAllFollowingBrandsUseCase(
                FetchAllFollowingBrandsUseCase.Params
            ).onSuccess { list ->
                if (list.isEmpty()) {
                    _uiState.value = FollowingUiState.Empty
                } else {
                    _uiState.value = FollowingUiState.Success(data = list.map { it.toUiModel() })
                }
            }.onFailure { throwable ->
                // no-op
            }
        }
    }

    fun handleDeleteClick(brandId: Int){
        viewModelScope.launch {
            removeBrandFromFollowingUseCase(
                RemoveBrandFromFollowingUseCase.Params(
                    brandId = brandId
                )
            ).onSuccess {
                initFollowingItems()
            }
        }
    }

}

sealed interface FollowingUiState {
    object Loading : FollowingUiState
    object Empty : FollowingUiState
    data class Success(
        val data: List<CategoryDetailItemUiModel>
    ) : FollowingUiState
}

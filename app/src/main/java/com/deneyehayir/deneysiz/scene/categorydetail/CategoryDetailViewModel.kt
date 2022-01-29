package com.deneyehayir.deneysiz.scene.categorydetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CategoryType
import com.deneyehayir.deneysiz.domain.usecase.FetchCategoryDetailUseCase
import com.deneyehayir.deneysiz.scene.categorydetail.model.SortOption
import com.deneyehayir.deneysiz.scene.categorydetail.model.toUiModel
import com.deneyehayir.deneysiz.scene.navCategoryId
import com.deneyehayir.deneysiz.scene.navCategoryStringRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val categoryDetail: FetchCategoryDetailUseCase
) : ViewModel() {
    private var currentViewState = CategoryDetailViewState.Initial
    private val _viewState = MutableStateFlow(currentViewState)
    val viewState: StateFlow<CategoryDetailViewState> = _viewState

    private val categoryId =
        savedStateHandle.get<String>(navCategoryId) ?: CategoryType.INVALID.type
    val categoryStringRes = savedStateHandle.get<Int>(navCategoryStringRes) ?: R.string.empty
    private var sortingJob: Job? = null

    val onSortSelected: ((SortOption) -> Unit) = { sortOption ->
        sortingJob?.cancel()
        sortingJob = viewModelScope.launch(Dispatchers.Default) {
            currentViewState = currentViewState.updateSortOption(sortOption)
            _viewState.value = currentViewState
        }
    }

    init {
        fetchCategoryDetailData()
    }

    private fun fetchCategoryDetailData() = viewModelScope.launch {
        categoryDetail(FetchCategoryDetailUseCase.Params(categoryId))
            .onSuccess { categoryDetail ->
                currentViewState =
                    currentViewState.updateBrandsList(categoryDetail.toUiModel().items)
                _viewState.value = currentViewState
            }
            .onFailure { Timber.d("fetchCategoryDetailData onFailure: $it") }
    }
}

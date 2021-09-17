package com.deneyehayir.deneysiz.scene.categorydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemUiModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailUiModel
import com.deneyehayir.deneysiz.domain.usecase.FetchCategoryDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val categoryDetail: FetchCategoryDetailUseCase
) : ViewModel() {
    // TODO manage loading state when api implemented
    private val loadingState = MutableStateFlow(true)
    private val brandsList = MutableStateFlow(emptyList<CategoryDetailItemUiModel>())
    val sortOption = MutableStateFlow(SortOption.SCORE_DESC)
    val viewState: StateFlow<CategoryDetailViewState> = combine(
        loadingState,
        brandsList,
        sortOption
    ) {
        loadingState, brandsList, sortOption ->
        CategoryDetailViewState(
            isLoading = loadingState,
            brandsList = sortBrands(sortOption, brandsList),
            sortOption = sortOption
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CategoryDetailViewState.Initial
    )

    init {
        fetchCategoryDetailData()
    }

    private fun fetchCategoryDetailData() = viewModelScope.launch {
        // TODO categoryId can be obtained by SavedStateHandle or @AssistedInject
        categoryDetail(FetchCategoryDetailUseCase.Params("1"))
            .onSuccess { postCategoryDetailData(it) }
            .onFailure { Timber.d("fetchCategoryDetailData onFailure: $it") }
    }

    private fun postCategoryDetailData(data: CategoryDetailUiModel) {
        brandsList.value = data.items
    }

    private fun sortBrands(
        sortOption: SortOption,
        brandsList: List<CategoryDetailItemUiModel>
    ): List<CategoryDetailItemUiModel> {
        return when (sortOption) {
            SortOption.ALPHABETICAL_ASC -> brandsList.sortedBy { it.brandName }
            SortOption.ALPHABETICAL_DESC -> brandsList.sortedByDescending { it.brandName }
            SortOption.SCORE_ASC -> brandsList.sortedBy { it.score }
            SortOption.SCORE_DESC -> brandsList.sortedByDescending { it.score }
        }
    }
}

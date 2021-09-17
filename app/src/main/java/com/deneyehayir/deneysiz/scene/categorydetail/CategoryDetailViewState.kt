package com.deneyehayir.deneysiz.scene.categorydetail

import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemUiModel

data class CategoryDetailViewState(
    val isLoading: Boolean,
    val brandsList: List<CategoryDetailItemUiModel>,
    val sortOption: SortOption
) {

    companion object {
        val Initial = CategoryDetailViewState(
            isLoading = true,
            brandsList = emptyList(),
            sortOption = SortOption.SCORE_DESC
        )
    }
}

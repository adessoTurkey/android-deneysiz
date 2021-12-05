package com.deneyehayir.deneysiz.scene.categorydetail

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.categorydetail.model.CategoryDetailItemUiModel
import com.deneyehayir.deneysiz.scene.categorydetail.model.SortOption

data class CategoryDetailViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    private val brandsList: List<CategoryDetailItemUiModel>,
    val sortOption: SortOption
) {

    val sortedBrandsList = when (sortOption) {
        SortOption.ALPHABETICAL_ASC -> brandsList.sortedBy { it.brandName }
        SortOption.ALPHABETICAL_DESC -> brandsList.sortedByDescending { it.brandName }
        SortOption.SCORE_ASC -> brandsList.sortedBy { it.score }
        SortOption.SCORE_DESC -> brandsList.sortedByDescending { it.score }
    }

    fun updateSortOption(sortOption: SortOption) = copy(
        sortOption = sortOption
    )

    fun updateBrandsList(brandsList: List<CategoryDetailItemUiModel>) = copy(
        brandsList = brandsList
    )

    companion object {
        val Initial = CategoryDetailViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            brandsList = emptyList(),
            sortOption = SortOption.SCORE_DESC
        )
    }
}

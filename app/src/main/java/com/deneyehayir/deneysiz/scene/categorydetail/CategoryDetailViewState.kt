package com.deneyehayir.deneysiz.scene.categorydetail

import com.deneyehayir.deneysiz.scene.categorydetail.model.CategoryDetailItemUiModel
import com.deneyehayir.deneysiz.scene.categorydetail.model.SortOption
import com.deneyehayir.deneysiz.scene.component.ErrorContentUiModel

data class CategoryDetailViewState(
    val isLoading: Boolean,
    val errorContent: ErrorContentUiModel?,
    private val brandsList: List<CategoryDetailItemUiModel>,
    val sortOption: SortOption
) {

    val sortedBrandsList = when (sortOption) {
        SortOption.ALPHABETICAL_ASC -> brandsList.sortedBy { it.brandName }
        SortOption.ALPHABETICAL_DESC -> brandsList.sortedByDescending { it.brandName }
        SortOption.SCORE_ASC -> brandsList.sortedBy { it.score }
        SortOption.SCORE_DESC -> brandsList.sortedByDescending { it.score }
    }

    val sortingOptions = SortOption.values().toList()

    fun updateSortOption(sortOption: SortOption) = copy(
        sortOption = sortOption
    )

    fun updateBrandsList(brandsList: List<CategoryDetailItemUiModel>) = copy(
        isLoading = false,
        brandsList = brandsList
    )

    fun showError(error: ErrorContentUiModel) = copy(
        isLoading = false,
        errorContent = error
    )

    fun hideError() = copy(
        isLoading = false,
        errorContent = null
    )

    fun updateForRemoveFavorite(brandId: Int) = copy(
        brandsList = brandsList.map { item ->
            if(item.id == brandId)
                item.copy(
                    isFavorite = false
                )
            else
                item
        }
    )

    fun updateForAddFavorite(brandId: Int) = copy(
        brandsList = brandsList.map { item ->
            if(item.id == brandId)
                item.copy(
                    isFavorite = true
                )
            else
                item
        }
    )

    companion object {
        val Initial = CategoryDetailViewState(
            isLoading = true,
            errorContent = null,
            brandsList = emptyList(),
            sortOption = SortOption.SCORE_DESC
        )
    }
}

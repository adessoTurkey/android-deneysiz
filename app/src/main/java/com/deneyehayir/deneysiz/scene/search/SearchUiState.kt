package com.deneyehayir.deneysiz.scene.search

import com.deneyehayir.deneysiz.scene.component.ErrorContentUiModel
import com.deneyehayir.deneysiz.scene.search.model.SearchResultItemUiModel


data class SearchUiState(
    val isLoading: Boolean,
    val isBrandNotFoundError: Boolean,
    val errorContent: ErrorContentUiModel?,
    val data: List<SearchResultItemUiModel>
) {
    fun updateSearchResult(searchResult: List<SearchResultItemUiModel>) = copy(
        isLoading = false, data = searchResult
    )

    fun showLoading() = copy(
        isLoading = true
    )

    fun showError(error: ErrorContentUiModel) = copy(
        isLoading = false, errorContent = error
    )

    fun hideError() = copy(
        isLoading = false, errorContent = null
    )

    fun cancelActions() = copy(
        isLoading = false, data = listOf(), isBrandNotFoundError = false
    )

    fun showBrandNotFoundError() = copy(
        isLoading = false, isBrandNotFoundError = true
    )

    fun hideBrandNotFoundError() = copy(
        isBrandNotFoundError = false
    )

    fun removeList() = copy(
        isBrandNotFoundError = false,
        data = listOf()
    )


    companion object {
        val Initial = SearchUiState(
            isLoading = false,
            isBrandNotFoundError = false,
            errorContent = null,
            data = listOf()
        )
    }
}

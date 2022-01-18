package com.deneyehayir.deneysiz.scene.discover

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.discover.model.CategoryUiModel

data class DiscoverViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val categoryUiModel: CategoryUiModel,
) {

    fun updateUiModel(categoryUiModel: CategoryUiModel) = DiscoverViewState(
        isLoading = false,
        shouldShowError = false,
        errorMessage = R.string.empty,
        categoryUiModel = categoryUiModel
    )

    // TODO handle errors
    fun setError() = DiscoverViewState(
        isLoading = false,
        shouldShowError = true,
        errorMessage = R.string.empty,
        categoryUiModel = CategoryUiModel.Empty
    )

    companion object {
        val Initial = DiscoverViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            categoryUiModel = CategoryUiModel.Empty
        )
    }
}

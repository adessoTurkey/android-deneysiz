package com.deneyehayir.deneysiz.scene.branddetail

import com.deneyehayir.deneysiz.scene.branddetail.model.BrandDetailUiModel
import com.deneyehayir.deneysiz.scene.component.ErrorContentUiModel

data class BrandDetailViewState(
    val isLoading: Boolean,
    val errorContent: ErrorContentUiModel?,
    val brandDetailData: BrandDetailUiModel?,
    val shouldScoreDetailDialogShown: Boolean
) {

    fun setBrandDetailData(data: BrandDetailUiModel) = copy(
        isLoading = false,
        errorContent = null,
        brandDetailData = data
    )

    fun changeScoreDetailDialogVisibility() = copy(
        shouldScoreDetailDialogShown = !shouldScoreDetailDialogShown
    )

    fun showError(error: ErrorContentUiModel) = copy(
        isLoading = false,
        errorContent = error
    )

    fun hideError() = copy(
        isLoading = false,
        errorContent = null
    )

    companion object {
        val Initial = BrandDetailViewState(
            isLoading = true,
            errorContent = null,
            brandDetailData = null,
            shouldScoreDetailDialogShown = false
        )
    }
}

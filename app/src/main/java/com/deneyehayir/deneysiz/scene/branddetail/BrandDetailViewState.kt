package com.deneyehayir.deneysiz.scene.branddetail

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.branddetail.model.BrandDetailUiModel

data class BrandDetailViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val brandDetailData: BrandDetailUiModel?,
    val shouldScoreDetailDialogShown: Boolean
) {

    fun startLoading() = copy(
        isLoading = true,
        shouldShowError = false
    )

    fun setBrandDetailData(data: BrandDetailUiModel) = copy(
        isLoading = false,
        shouldShowError = false,
        brandDetailData = data
    )

    fun changeScoreDetailDialogVisibility() = copy(
        shouldScoreDetailDialogShown = !shouldScoreDetailDialogShown
    )

    companion object {
        val Initial = BrandDetailViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            brandDetailData = null,
            shouldScoreDetailDialogShown = false
        )
    }
}

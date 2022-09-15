package com.deneyehayir.deneysiz.scene.whoweare

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.whoweare.model.WhoWeAreUiModel

data class WhoWeAreViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val whoWeAreUiModel: WhoWeAreUiModel?
) {
    fun updateWhoWeAreViewState(whoWeAreUiModel: WhoWeAreUiModel) = copy(
        isLoading = false,
        shouldShowError = false,
        whoWeAreUiModel = whoWeAreUiModel
    )

    fun setError() = copy(
        shouldShowError = true
    )

    companion object {
        val Initial = WhoWeAreViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            whoWeAreUiModel = null
        )
    }
}

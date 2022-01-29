package com.deneyehayir.deneysiz.scene.doyouknow

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.doyouknow.model.DoYouKnowUiModel

data class DoYouKnowViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val doYouKnowUiModel: DoYouKnowUiModel?
) {

    fun updateDoYouKnowUiModel(doYouKnowUiModel: DoYouKnowUiModel) = copy(
        isLoading = false,
        shouldShowError = false,
        doYouKnowUiModel = doYouKnowUiModel
    )

    fun setError() = copy(
        shouldShowError = true
    )

    companion object {
        val Initial = DoYouKnowViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            doYouKnowUiModel = null
        )
    }
}

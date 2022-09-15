package com.deneyehayir.deneysiz.scene.doyouknowcontent

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.doyouknowcontent.model.DoYouKnowContentUiModel

data class DoYouKnowContentViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val contentUiModel: DoYouKnowContentUiModel?
) {

    fun updateContentUiModel(contentUiModel: DoYouKnowContentUiModel) = copy(
        isLoading = false,
        shouldShowError = false,
        contentUiModel = contentUiModel
    )

    fun setError() = copy(
        shouldShowError = true
    )

    companion object {
        val Initial = DoYouKnowContentViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            contentUiModel = null
        )
    }
}

package com.deneyehayir.deneysiz.scene.faq

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.faq.model.FaqItemUiModel

data class FaqViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val faqItemUiModel: FaqItemUiModel?
) {

    fun updateDoYouKnowUiModel(faqItemUiModel: FaqItemUiModel) = copy(
        isLoading = false,
        shouldShowError = false,
        faqItemUiModel = faqItemUiModel
    )

    fun setError() = copy(
        shouldShowError = true
    )

    companion object {
        val Initial = FaqViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            faqItemUiModel = null
        )
    }
}

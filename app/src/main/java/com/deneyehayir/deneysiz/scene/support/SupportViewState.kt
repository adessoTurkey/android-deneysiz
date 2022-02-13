package com.deneyehayir.deneysiz.scene.support

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.support.model.SupportActionUiModel
import com.deneyehayir.deneysiz.scene.support.model.SupportUiModel

data class SupportViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val supportUiModel: SupportUiModel?
) {
    fun updateSupportViewState(supportUiModel: SupportUiModel) = copy(
        isLoading = false,
        shouldShowError = false,
        supportUiModel = supportUiModel
    )

    fun setError() = copy(
        shouldShowError = true
    )

    fun updateExpandableState(supportAction: SupportActionUiModel) = copy(
        supportUiModel = supportUiModel?.copy(
            supportActions = supportUiModel.supportActions.map { item ->
                if (item == supportAction) item.updateExpandableState() else item
            }
        )
    )

    companion object {
        val Initial = SupportViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            supportUiModel = null
        )
    }
}

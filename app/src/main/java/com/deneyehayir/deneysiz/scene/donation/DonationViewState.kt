package com.deneyehayir.deneysiz.scene.donation

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.donation.model.DonationUiModel

data class DonationViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val donationUiModel: DonationUiModel?
) {

    fun updateDonationUiModel(donationUiModel: DonationUiModel) = copy(
        isLoading = false,
        shouldShowError = false,
        donationUiModel = donationUiModel
    )

    fun setError() = copy(
        shouldShowError = true
    )

    companion object {
        val Initial = DonationViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            donationUiModel = null
        )
    }
}

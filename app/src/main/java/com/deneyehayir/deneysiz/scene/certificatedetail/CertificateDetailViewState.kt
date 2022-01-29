package com.deneyehayir.deneysiz.scene.certificatedetail

import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.scene.certificatedetail.model.CertificateDetailUiModel

data class CertificateDetailViewState(
    val isLoading: Boolean,
    val shouldShowError: Boolean,
    @StringRes val errorMessage: Int,
    val certificateDetail: CertificateDetailUiModel?
) {

    fun updateCertificateDetail(certificateDetail: CertificateDetailUiModel) = copy(
        isLoading = false,
        shouldShowError = false,
        certificateDetail = certificateDetail
    )

    fun setError() = copy(
        shouldShowError = true
    )

    companion object {
        val Initial = CertificateDetailViewState(
            isLoading = true,
            shouldShowError = false,
            errorMessage = R.string.empty,
            certificateDetail = null
        )
    }
}

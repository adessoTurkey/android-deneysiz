package com.deneyehayir.deneysiz.scene.certificatedetail.model

import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CertificateItemDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificateType
import com.deneyehayir.deneysiz.internal.extension.getDisplayUrl

data class CertificateDetailUiModel(
    val certificate: CertificateType,
    val url: String
) {
    val displayUrl = url.getDisplayUrl()
    val displayName = certificate.name
    val descriptionRes = when (certificate) {
        CertificateType.LeapingBunny -> R.string.certificate_detail_description_leaping_bunny
        CertificateType.BeautyWithoutBunnies -> {
            R.string.certificate_detail_description_beauty_without_bunnies
        }
        CertificateType.VeganSociety -> R.string.certificate_detail_description_vegan_society
        CertificateType.VLabel -> R.string.certificate_detail_description_v_label
        CertificateType.None -> R.string.empty
    }
    val iconRes = when (certificate) {
        CertificateType.LeapingBunny -> R.drawable.ic_certificate_leaping_bunny
        CertificateType.BeautyWithoutBunnies -> R.drawable.ic_certificate_beauty_without_bunnies
        CertificateType.VeganSociety -> R.drawable.ic_certificate_vegan_society
        CertificateType.VLabel -> R.drawable.ic_certificate_v_label
        CertificateType.None -> R.drawable.ic_do_you_know
    }
}

fun CertificateItemDomainModel.toUiModel() = CertificateDetailUiModel(
    certificate = certificate,
    url = url
)

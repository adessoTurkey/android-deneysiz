package com.deneyehayir.deneysiz.data.local.model

import com.deneyehayir.deneysiz.domain.model.BEAUTY_WITHOUT_BUNNIES
import com.deneyehayir.deneysiz.domain.model.CertificateItemDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificateType
import com.deneyehayir.deneysiz.domain.model.CertificatesDomainModel
import com.deneyehayir.deneysiz.domain.model.LEAPING_BUNNY
import com.deneyehayir.deneysiz.domain.model.VEGAN_SOCIETY
import com.deneyehayir.deneysiz.domain.model.V_LABEL
import kotlinx.serialization.Serializable

// value class?
@Serializable
data class CertificateDataModel(
    val certificates: List<CertificateItemDataModel>
)

@Serializable
data class CertificateItemDataModel(
    val name: String,
    val url: String
)

fun CertificateDataModel.toDomain() = CertificatesDomainModel(
    certificates = certificates.map { certificate -> certificate.toDomain() }
)

fun CertificateItemDataModel.toDomain() = CertificateItemDomainModel(
    certificate = when (name) {
        LEAPING_BUNNY -> CertificateType.LeapingBunny
        BEAUTY_WITHOUT_BUNNIES -> CertificateType.BeautyWithoutBunnies
        VEGAN_SOCIETY -> CertificateType.VeganSociety
        V_LABEL -> CertificateType.VLabel
        else -> CertificateType.None
    },
    url = url
)

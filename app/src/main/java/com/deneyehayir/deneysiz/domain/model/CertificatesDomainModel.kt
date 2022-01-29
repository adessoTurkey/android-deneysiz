package com.deneyehayir.deneysiz.domain.model

@JvmInline
value class CertificatesDomainModel(
    val certificates: List<CertificateItemDomainModel>
)

data class CertificateItemDomainModel(
    val certificate: CertificateType,
    val url: String
) {
    companion object {
        val Empty = CertificateItemDomainModel(
            certificate = CertificateType.None,
            url = ""
        )
    }
}

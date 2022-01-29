package com.deneyehayir.deneysiz.domain.model

data class DoYouKnowDomainModel(
    val description: String,
    val certificates: List<CertificateItemDomainModel>,
    val faqTitle: String,
    val faqDescription: String,
    val faqList: List<FaqItemDomainModel>
)

data class FaqItemDomainModel(
    val id: Int,
    val title: String,
    val description: String
) {
    companion object {
        val Empty = FaqItemDomainModel(
            id = -1,
            title = "",
            description = ""
        )
    }
}

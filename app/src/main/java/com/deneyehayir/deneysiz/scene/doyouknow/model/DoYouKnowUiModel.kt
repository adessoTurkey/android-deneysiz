package com.deneyehayir.deneysiz.scene.doyouknow.model

import com.deneyehayir.deneysiz.domain.model.DoYouKnowDomainModel
import com.deneyehayir.deneysiz.domain.model.FaqItemDomainModel
import com.deneyehayir.deneysiz.scene.branddetail.model.CertificateUiModel

data class DoYouKnowUiModel(
    val description: String,
    val certificates: List<CertificateUiModel>,
    val faqTitle: String,
    val faqDescription: String,
    val faqList: List<FaqItemUiModel>
) {
    val windowedCertificates = certificates.windowed(2, 2, true)
}

data class FaqItemUiModel(
    val id: Int,
    val title: String
)

fun FaqItemDomainModel.toUiModel() = FaqItemUiModel(
    id = id,
    title = title
)

// common uiModels should be organized
fun DoYouKnowDomainModel.toUiModel() = DoYouKnowUiModel(
    description = description,
    certificates = certificates.map { certificate ->
        CertificateUiModel(
            certificate = certificate.certificate,
            isAvailable = true
        )
    },
    faqTitle = faqTitle,
    faqDescription = faqDescription,
    faqList = faqList.map { faq -> faq.toUiModel() }
)

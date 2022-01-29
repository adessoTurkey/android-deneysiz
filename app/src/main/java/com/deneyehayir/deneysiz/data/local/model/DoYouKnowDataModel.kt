package com.deneyehayir.deneysiz.data.local.model

import com.deneyehayir.deneysiz.domain.model.DoYouKnowDomainModel
import com.deneyehayir.deneysiz.domain.model.FaqItemDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class DoYouKnowDataModel(
    val description: String,
    val certificates: List<CertificateItemDataModel>,
    val faqTitle: String,
    val faqDescription: String,
    val faqList: List<FaqItemDataModel>
)

@Serializable
data class FaqItemDataModel(
    val id: Int,
    val title: String,
    val description: String
)

fun DoYouKnowDataModel.toDomain() = DoYouKnowDomainModel(
    description = description,
    certificates = certificates.map { certificate -> certificate.toDomain() },
    faqTitle = faqTitle,
    faqDescription = faqDescription,
    faqList = faqList.map { faqItem -> faqItem.toDomain() }
)

fun FaqItemDataModel.toDomain() = FaqItemDomainModel(
    id = id,
    title = title,
    description = description
)

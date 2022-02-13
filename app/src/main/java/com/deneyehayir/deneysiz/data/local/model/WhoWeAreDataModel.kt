package com.deneyehayir.deneysiz.data.local.model

import com.deneyehayir.deneysiz.domain.model.SocialMediaPageDomainModel
import com.deneyehayir.deneysiz.domain.model.WhoWeAreDomainModel
import com.deneyehayir.deneysiz.domain.model.WhoWeAreSupportActionDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class WhoWeAreDataModel(
    val description: String,
    val url: String,
    val supportActions: List<WhoWeAreSupportActionDataModel>,
    val footerDescription: String,
    val socialMediaPages: List<SocialMediaPageDataModel>
)

@Serializable
data class WhoWeAreSupportActionDataModel(
    val id: Int,
    val text: String
)

@Serializable
data class SocialMediaPageDataModel(
    val id: Int,
    val url: String
)

fun WhoWeAreDataModel.toDomain() = WhoWeAreDomainModel(
    description = description,
    url = url,
    supportActions = supportActions.map { supportAction -> supportAction.toDomain() },
    footerDescription = footerDescription,
    socialMediaPages = socialMediaPages.map { socialMedia -> socialMedia.toDomain() }
)

fun WhoWeAreSupportActionDataModel.toDomain() = WhoWeAreSupportActionDomainModel(
    id = id,
    text = text
)

fun SocialMediaPageDataModel.toDomain() = SocialMediaPageDomainModel(
    id = id,
    url = url
)

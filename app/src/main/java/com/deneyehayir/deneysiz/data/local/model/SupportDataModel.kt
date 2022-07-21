package com.deneyehayir.deneysiz.data.local.model

import com.deneyehayir.deneysiz.domain.model.SupportActionDomainModel
import com.deneyehayir.deneysiz.domain.model.SupportDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class SupportDataModel(
    val description: String,
    val supportActions: List<SupportActionDataModel>
)

@Serializable
data class SupportActionDataModel(
    val id: Int,
    val title: String,
    val description: String,
    val socialMediaPages: List<SocialMediaPageDataModel>
)

fun SupportDataModel.toDomain() = SupportDomainModel(
    description = description,
    supportActions = supportActions.map { supportAction -> supportAction.toDomain() }
)

fun SupportActionDataModel.toDomain() = SupportActionDomainModel(
    id = id,
    title = title,
    description = description,
    socialMediaPages = socialMediaPages.map { socialMediaPage -> socialMediaPage.toDomain() }
)

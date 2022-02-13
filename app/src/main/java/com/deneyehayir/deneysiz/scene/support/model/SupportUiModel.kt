package com.deneyehayir.deneysiz.scene.support.model

import com.deneyehayir.deneysiz.domain.model.SupportActionDomainModel
import com.deneyehayir.deneysiz.domain.model.SupportDomainModel
import com.deneyehayir.deneysiz.scene.whoweare.model.SocialMediaPageUiModel
import com.deneyehayir.deneysiz.scene.whoweare.model.toUiModel

data class SupportUiModel(
    val description: String,
    val supportActions: List<SupportActionUiModel>,
    val volunteerUrl: String
)

data class SupportActionUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val socialMediaPages: List<SocialMediaPageUiModel>,
    val isExpanded: Boolean
) {
    fun updateExpandableState() = copy(
        isExpanded = !isExpanded
    )
}

fun SupportDomainModel.toUiModel() = SupportUiModel(
    description = description,
    supportActions = supportActions.map { supportAction -> supportAction.toUiModel() },
    volunteerUrl = volunteerUrl
)

fun SupportActionDomainModel.toUiModel() = SupportActionUiModel(
    id = id,
    title = title,
    description = description,
    socialMediaPages = socialMediaPages.map { socialMediaPage -> socialMediaPage.toUiModel() },
    isExpanded = false
)

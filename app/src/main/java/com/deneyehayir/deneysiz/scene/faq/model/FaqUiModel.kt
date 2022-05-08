package com.deneyehayir.deneysiz.scene.faq.model

import com.deneyehayir.deneysiz.domain.model.FaqItemDomainModel
import com.deneyehayir.deneysiz.domain.model.FaqItemLinkDomainModel

data class FaqItemUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val links: List<FaqItemLinkUiModel>
)

data class FaqItemLinkUiModel(
    val displayName: String,
    val url: String
)

fun FaqItemDomainModel.toUiModel() = FaqItemUiModel(
    id = id,
    title = title,
    description = description,
    links = links.map { linkItem -> linkItem.toUiModel() }
)

fun FaqItemLinkDomainModel.toUiModel() = FaqItemLinkUiModel(
    displayName = name,
    url = url
)

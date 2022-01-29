package com.deneyehayir.deneysiz.scene.faq.model

import com.deneyehayir.deneysiz.domain.model.FaqItemDomainModel

data class FaqItemUiModel(
    val id: Int,
    val title: String,
    val description: String
)

fun FaqItemDomainModel.toUiModel() = FaqItemUiModel(
    id = id,
    title = title,
    description = description
)

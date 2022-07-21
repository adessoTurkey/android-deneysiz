package com.deneyehayir.deneysiz.scene.doyouknowcontent.model

import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.DoYouKnowContentDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.ParagraphDomainData

data class DoYouKnowContentUiModel(
    val id: Int,
    val paragraphs: List<ParagraphDomainData>
) {
    val titleIcons: List<Int> = when (id) {
        0 -> listOf(R.drawable.ic_certificate_detail_leaping_bunny)
        1 -> listOf(R.drawable.ic_certificate_detail_vegan_society)
        2 -> listOf(
            R.drawable.ic_certificate_detail_beauty_without_bunnies_1,
            R.drawable.ic_certificate_detail_beauty_without_bunnies_2
        )
        3 -> listOf(
            R.drawable.ic_certificate_detail_v_label_1,
            R.drawable.ic_certificate_detail_v_label_2
        )
        else -> emptyList()
    }

    val footerIcons: List<Int> = when (id) {
        2 -> listOf(
            R.drawable.ic_certificate_detail_beauty_without_bunnies_3,
            R.drawable.ic_certificate_detail_beauty_without_bunnies_4,
            R.drawable.ic_certificate_detail_beauty_without_bunnies_5,
            R.drawable.ic_certificate_detail_beauty_without_bunnies_6
        )
        else -> emptyList()
    }
}

fun DoYouKnowContentDetailDomainModel.toUiModel() = DoYouKnowContentUiModel(
    id = id,
    paragraphs = paragraphs
)

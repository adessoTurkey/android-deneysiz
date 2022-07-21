package com.deneyehayir.deneysiz.domain.model

data class DoYouKnowContentDomainModel(
    val contentDetails: List<DoYouKnowContentDetailDomainModel>
)

data class DoYouKnowContentDetailDomainModel(
    val id: Int,
    val paragraphs: List<ParagraphDomainData>
) {
    companion object {
        val EMPTY = DoYouKnowContentDetailDomainModel(
            id = -1,
            paragraphs = emptyList()
        )
    }
}

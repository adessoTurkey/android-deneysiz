package com.deneyehayir.deneysiz.data.local.model

import com.deneyehayir.deneysiz.domain.model.DoYouKnowContentDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.DoYouKnowContentDomainModel
import com.deneyehayir.deneysiz.domain.model.MarkupDomainData
import com.deneyehayir.deneysiz.domain.model.MarkupType
import com.deneyehayir.deneysiz.domain.model.ParagraphDomainData
import com.deneyehayir.deneysiz.domain.model.ParagraphType
import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class DoYouKnowContentDataModel(
    val contentDetails: List<DoYouKnowContentDetailDataModel>
)

@Serializable
data class DoYouKnowContentDetailDataModel(
    val id: Int,
    val paragraphs: List<ParagraphDataModel>
)

@Serializable
data class ParagraphDataModel(
    val type: ParagraphType,
    val content: String,
    val markups: List<MarkupDataModel> = emptyList()
)

@Serializable
data class MarkupDataModel(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null
)

fun DoYouKnowContentDataModel.toDomain() = DoYouKnowContentDomainModel(
    contentDetails = contentDetails.map { content ->
        content.toDomain()
    }
)

fun DoYouKnowContentDetailDataModel.toDomain() = DoYouKnowContentDetailDomainModel(
    id = id,
    paragraphs = paragraphs.map { paragraph ->
        paragraph.toDomain()
    }
)

fun ParagraphDataModel.toDomain() = ParagraphDomainData(
    type = type,
    content = content,
    markups = markups.map { markup ->
        markup.toDomain()
    }
)

fun MarkupDataModel.toDomain() = MarkupDomainData(
    type = type,
    start = start,
    end = end,
    href = href
)

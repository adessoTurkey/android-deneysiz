package com.deneyehayir.deneysiz.domain.model

data class ParagraphDomainData(
    val type: ParagraphType,
    val content: String,
    val markups: List<MarkupDomainData> = emptyList()
)

data class MarkupDomainData(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null
)

enum class MarkupType {
    Link,
    Bold
}

enum class ParagraphType {
    Title,
    Header,
    Text
}

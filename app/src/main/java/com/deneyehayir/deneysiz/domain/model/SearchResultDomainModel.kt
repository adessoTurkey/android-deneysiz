package com.deneyehayir.deneysiz.domain.model

data class SearchResultDomainModel(
    val items: List<SearchResultItemDomainModel>,
    val shouldShowError: Boolean
)

data class SearchResultItemDomainModel(
    val id: Int,
    val brandName: String,
    val parentCompanyName: String,
    val score: Int
)

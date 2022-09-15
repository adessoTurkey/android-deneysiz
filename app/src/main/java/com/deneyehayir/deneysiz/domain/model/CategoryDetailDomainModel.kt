package com.deneyehayir.deneysiz.domain.model

data class CategoryDetailDomainModel(
    val items: List<CategoryDetailItemDomainModel>,
    val shouldShowError: Boolean
)

data class CategoryDetailItemDomainModel(
    val id: Int,
    val brandName: String,
    val parentCompanyName: String,
    val score: Int,
)

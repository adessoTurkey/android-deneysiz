package com.deneyehayir.deneysiz.domain.model

@JvmInline
value class CategoryDetailDomainModel(
    val items: List<CategoryDetailItemDomainModel>
)

data class CategoryDetailItemDomainModel(
    val id: Int,
    val brandName: String,
    val parentCompanyName: String,
    val score: Int,
)

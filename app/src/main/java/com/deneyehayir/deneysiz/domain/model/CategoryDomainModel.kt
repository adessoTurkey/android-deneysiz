package com.deneyehayir.deneysiz.domain.model

@JvmInline
value class CategoryDomainModel(
    val items: List<CategoryItemDomainModel>
)

data class CategoryItemDomainModel(
    val type: CategoryType,
    val categoryName: String
)

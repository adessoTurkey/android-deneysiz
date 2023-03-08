package com.deneyehayir.deneysiz.domain.model

import com.deneyehayir.deneysiz.data.local.database.entity.BrandEntity

data class CategoryDetailDomainModel(
    val items: List<CategoryDetailItemDomainModel>,
    val shouldShowError: Boolean
)

data class CategoryDetailItemDomainModel(
    val id: Int,
    val brandName: String,
    val parentCompanyName: String,
    val score: Int,
    val isFavorite: Boolean
) {
    fun toEntity() = BrandEntity(
        brandId = id,
        brandName = brandName,
        parentCompanyName = parentCompanyName,
        score = score,
        isFavorite = isFavorite
    )
}

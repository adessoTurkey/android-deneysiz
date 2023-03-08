package com.deneyehayir.deneysiz.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemDomainModel

@Entity(tableName = "brands")
data class BrandEntity(
    @PrimaryKey @ColumnInfo(name = "brand_id") val brandId: Int,
    @ColumnInfo(name = "brand_name") val brandName: String,
    @ColumnInfo(name = "parent_company_name") val parentCompanyName: String,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
) {
    fun toDomainModel() = CategoryDetailItemDomainModel(
        id = brandId,
        brandName = brandName,
        parentCompanyName = parentCompanyName,
        score = score,
        isFavorite = isFavorite
    )
}

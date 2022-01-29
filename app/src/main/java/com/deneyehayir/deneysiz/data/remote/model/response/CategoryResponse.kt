package com.deneyehayir.deneysiz.data.remote.model.response

import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryItemDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryType
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class CategoryResponse(
    val items: List<CategoryItemResponse>?
)

@Serializable
data class CategoryItemResponse(
    val id: String?,
    val category: String?
)

fun CategoryResponse.toDomain() = CategoryDomainModel(
    items = items?.map { response ->
        CategoryItemDomainModel(
            type = when (response.id) {
                "0" -> CategoryType.MAKEUP
                "1" -> CategoryType.HAIR_CARE
                "2" -> CategoryType.SKIN_CARE
                "3" -> CategoryType.FRAGRANCE
                "4" -> CategoryType.PERSONAL_CARE
                "5" -> CategoryType.DENTAL_CARE
                "6" -> CategoryType.MOM_BABY_CARE
                "7" -> CategoryType.HOME_CARE
                "8" -> CategoryType.ALL_BRANDS
                else -> CategoryType.INVALID
            },
            categoryName = response.category.orEmpty()
        )
    }.orEmpty()
)

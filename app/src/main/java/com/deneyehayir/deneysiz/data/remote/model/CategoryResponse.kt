package com.deneyehayir.deneysiz.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class CategoryResponse(
    val items: List<CategoryItemResponse>
)

@Serializable
data class CategoryItemResponse(
    val id: CategoryType,
    val category: String
)

@Serializable
enum class CategoryType(val type: String) {
    @SerialName("8")
    ALL_BRANDS("8"),
    @SerialName("0")
    MAKEUP("0"),
    @SerialName("1")
    HAIR_CARE("1"),
    @SerialName("2")
    SKIN_CARE("2"),
    @SerialName("3")
    FRAGRANCE("3"),
    @SerialName("4")
    PERSONAL_CARE("4"),
    @SerialName("5")
    DENTAL_CARE("5"),
    @SerialName("6")
    MOM_BABY_CARE("6"),
    @SerialName("7")
    HOME_CARE("7")
}

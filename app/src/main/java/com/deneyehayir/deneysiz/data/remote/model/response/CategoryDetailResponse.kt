package com.deneyehayir.deneysiz.data.remote.model.response

import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDetailResponse(
    val status: Int?,
    val message: String?,
    val data: List<CategoryDetailItemResponse>?
)

@Serializable
data class CategoryDetailItemResponse(
    val id: Int?,
    val name: String?,
    val parentCompany: String?,
    val score: Int?,
    val certificate: List<CertificateResponse>?,
    val offerInChina: Boolean?,
    val parentCompanySafe: Boolean?,
    val isSafe: Boolean?,
    val vegan: Boolean?,
    val hasVeganProduct: Boolean?,
)

fun CategoryDetailResponse.toDomain() = CategoryDetailDomainModel(
    items = data?.map { response ->
        CategoryDetailItemDomainModel(
            id = response.id ?: -1,
            brandName = response.name.orEmpty(),
            parentCompanyName = response.parentCompany.orEmpty(),
            score = response.score ?: -1
        )
    }.orEmpty()
)

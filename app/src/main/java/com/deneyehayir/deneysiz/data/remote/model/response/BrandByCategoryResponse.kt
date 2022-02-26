package com.deneyehayir.deneysiz.data.remote.model.response

import com.deneyehayir.deneysiz.domain.model.BEAUTY_WITHOUT_BUNNIES
import com.deneyehayir.deneysiz.domain.model.BrandDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificateDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificateType
import com.deneyehayir.deneysiz.domain.model.LEAPING_BUNNY
import com.deneyehayir.deneysiz.domain.model.ParentCompanyType
import com.deneyehayir.deneysiz.domain.model.VEGAN_SOCIETY
import com.deneyehayir.deneysiz.domain.model.V_LABEL
import kotlinx.serialization.Serializable

@Serializable
data class BrandByCategoryResponse(
    val status: Int?,
    val message: String?,
    val data: List<BrandByCategoryItemResponse>?
)

@Serializable
data class BrandByCategoryItemResponse(
    val id: Int?,
    val name: String?,
    val parentCompany: ParentCompanyResponse?,
    val offerInChina: Boolean?,
    val categoryId: String?,
    val certificates: List<CertificateResponse>?,
    val safe: Boolean?,
    val vegan: Boolean?,
    val veganProduct: Boolean?,
    val score: Int?,
    val description: String?,
    val createdAt: String?,
)

@Serializable
data class ParentCompanyResponse(
    val name: String?,
    val safe: Boolean?
)

@Serializable
data class CertificateResponse(
    val name: String?,
    val valid: Boolean?
)

fun BrandByCategoryResponse.toCategoryDetailDomain() = CategoryDetailDomainModel(
    items = data?.map { response ->
        CategoryDetailItemDomainModel(
            id = response.id ?: -1,
            brandName = response.name.orEmpty(),
            parentCompanyName = response.parentCompany?.name.orEmpty(),
            score = response.score ?: -1
        )
    }.orEmpty(),
    shouldShowError = status != 200
)

fun BrandByCategoryResponse.toBrandDetailDomain() = BrandDetailDomainModel(
    id = data?.firstOrNull()?.id ?: -1,
    name = data?.firstOrNull()?.name.orEmpty(),
    parentCompany = data?.firstOrNull()?.parentCompany.toParentCompanyType(),
    isOfferedInChina = data?.firstOrNull()?.offerInChina ?: false,
    categoryId = data?.firstOrNull()?.categoryId.orEmpty(),
    certificates = data?.firstOrNull()?.certificates?.map { it.toDomain() } ?: emptyList(),
    isSafe = data?.firstOrNull()?.safe ?: false,
    isVegan = data?.firstOrNull()?.vegan ?: false,
    isVeganProduct = data?.firstOrNull()?.veganProduct ?: false,
    score = data?.firstOrNull()?.score ?: -1,
    description = data?.firstOrNull()?.description.orEmpty(),
    updateDate = data?.firstOrNull()?.createdAt.orEmpty(),
    shouldShowError = status != 200
)

fun ParentCompanyResponse?.toParentCompanyType(): ParentCompanyType {
    return if (this == null) {
        ParentCompanyType.Unavailable
    } else {
        ParentCompanyType.Available(
            name = name.orEmpty(),
            safe = safe ?: false
        )
    }
}

fun CertificateResponse?.toDomain() = CertificateDomainModel(
    certificate = when (this?.name) {
        LEAPING_BUNNY -> CertificateType.LeapingBunny
        BEAUTY_WITHOUT_BUNNIES -> CertificateType.BeautyWithoutBunnies
        VEGAN_SOCIETY -> CertificateType.VeganSociety
        V_LABEL -> CertificateType.VLabel
        else -> CertificateType.None
    },
    valid = this?.valid ?: false
)

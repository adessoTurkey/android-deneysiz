package com.deneyehayir.deneysiz.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDetailResponse(
    val status: Int,
    val message: String,
    val data: List<CategoryDetailItemResponse>
)

@Serializable
data class CategoryDetailItemResponse(
    val id: Int,
    val name: String,
    val parentCompany: String?,
    val score: Int,
    val certificate: List<CertificateResponse>,
    val offerInChina: Boolean,
    val parentCompanySafe: Boolean,
    val isSafe: Boolean,
    val vegan: Boolean,
    val hasVeganProduct: Boolean,
)

@Serializable
data class CertificateResponse(
    val name: String,
    val valid: Boolean
)

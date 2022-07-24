package com.deneyehayir.deneysiz.domain.model

data class BrandDetailDomainModel(
    val id: Int,
    val name: String,
    val parentCompany: ParentCompanyType,
    val isOfferedInChina: Boolean,
    val categoryId: List<Int>,
    val certificates: List<CertificateDomainModel>,
    val isSafe: Boolean,
    val isVegan: Boolean,
    val isVeganProduct: Boolean,
    val score: Int,
    val description: String,
    val updateDate: String,
    val shouldShowError: Boolean
)

data class CertificateDomainModel(
    val certificate: CertificateType,
    val valid: Boolean
)

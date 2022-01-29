package com.deneyehayir.deneysiz.domain.repository

import com.deneyehayir.deneysiz.domain.model.BrandDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificatesDomainModel
import com.deneyehayir.deneysiz.domain.model.DoYouKnowDomainModel

interface Repository {

    suspend fun fetchCategories(): CategoryDomainModel

    suspend fun fetchCategoryDetail(categoryId: String): CategoryDetailDomainModel

    suspend fun fetchBrandDetail(brandId: Int): BrandDetailDomainModel

    suspend fun fetchCertificates(): CertificatesDomainModel

    suspend fun fetchDoYouKnowData(): DoYouKnowDomainModel
}

package com.deneyehayir.deneysiz.domain.repository

import com.deneyehayir.deneysiz.domain.model.*

interface Repository {

    suspend fun fetchCategories(): CategoryDomainModel

    suspend fun fetchCategoryDetail(categoryId: String): CategoryDetailDomainModel

    suspend fun fetchBrandDetail(brandId: Int): BrandDetailDomainModel

    suspend fun fetchCertificates(): CertificatesDomainModel

    suspend fun fetchDoYouKnowData(): DoYouKnowDomainModel

    suspend fun fetchWhoWeAreData(): WhoWeAreDomainModel

    suspend fun fetchDonationData(): DonationDomainModel

    suspend fun fetchSupportData(): SupportDomainModel

    suspend fun fetchDoYouKnowContentData(): DoYouKnowContentDomainModel

    suspend fun fetchSearchResult(query: String): SearchResultDomainModel
}

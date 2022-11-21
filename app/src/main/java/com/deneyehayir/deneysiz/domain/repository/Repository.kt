package com.deneyehayir.deneysiz.domain.repository

import com.deneyehayir.deneysiz.domain.model.BrandDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificatesDomainModel
import com.deneyehayir.deneysiz.domain.model.DoYouKnowContentDomainModel
import com.deneyehayir.deneysiz.domain.model.DoYouKnowDomainModel
import com.deneyehayir.deneysiz.domain.model.DonationDomainModel
import com.deneyehayir.deneysiz.domain.model.SupportDomainModel
import com.deneyehayir.deneysiz.domain.model.WhoWeAreDomainModel

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

    suspend fun addBrandToFollowing(categoryDetailItemDomainModel: CategoryDetailItemDomainModel): Long

    suspend fun removeBrandFromFollowing(brandId: Int)

    suspend fun fetchFollowingBrands() : List<CategoryDetailItemDomainModel>
}

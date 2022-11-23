package com.deneyehayir.deneysiz.data.repository

import com.deneyehayir.deneysiz.data.local.datasource.AssetDataSource
import com.deneyehayir.deneysiz.data.local.model.toDomain
import com.deneyehayir.deneysiz.data.remote.datasource.RemoteDataSource
import com.deneyehayir.deneysiz.data.remote.model.request.BrandDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.SearchBrandsRequestBody
import com.deneyehayir.deneysiz.data.remote.model.response.toBrandDetailDomain
import com.deneyehayir.deneysiz.data.remote.model.response.toCategoryDetailDomain
import com.deneyehayir.deneysiz.data.remote.model.response.toSearchResultDomainModel
import com.deneyehayir.deneysiz.domain.model.BrandDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificatesDomainModel
import com.deneyehayir.deneysiz.domain.model.DoYouKnowContentDomainModel
import com.deneyehayir.deneysiz.domain.model.DoYouKnowDomainModel
import com.deneyehayir.deneysiz.domain.model.DonationDomainModel
import com.deneyehayir.deneysiz.domain.model.SearchResultDomainModel
import com.deneyehayir.deneysiz.domain.model.SupportDomainModel
import com.deneyehayir.deneysiz.domain.model.WhoWeAreDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val assetDataSource: AssetDataSource
) : Repository {

    override suspend fun fetchCategoryDetail(
        categoryId: String
    ): CategoryDetailDomainModel = remoteDataSource.fetchCategoryDetail(
        requestBody = CategoryDetailRequestBody(
            categoryId = categoryId
        )
    ).toCategoryDetailDomain()

    override suspend fun fetchBrandDetail(brandId: Int): BrandDetailDomainModel =
        remoteDataSource.fetchBrandDetail(
            requestBody = BrandDetailRequestBody(
                id = brandId
            )
        ).toBrandDetailDomain()

    override suspend fun fetchSearchResult(query: String): SearchResultDomainModel =
        remoteDataSource.fetchSearchResult(SearchBrandsRequestBody(query = query))
            .toSearchResultDomainModel()

    override suspend fun fetchCategories(): CategoryDomainModel =
        assetDataSource.getCategories().toDomain()

    override suspend fun fetchCertificates(): CertificatesDomainModel =
        assetDataSource.getCertificates().toDomain()

    override suspend fun fetchDoYouKnowData(): DoYouKnowDomainModel =
        assetDataSource.getDoYouKnowData().toDomain()

    override suspend fun fetchWhoWeAreData(): WhoWeAreDomainModel =
        assetDataSource.getWhoWeAreData().toDomain()

    override suspend fun fetchDonationData(): DonationDomainModel =
        assetDataSource.getDonationData().toDomain()

    override suspend fun fetchSupportData(): SupportDomainModel =
        assetDataSource.getSupportData().toDomain()

    override suspend fun fetchDoYouKnowContentData(): DoYouKnowContentDomainModel =
        assetDataSource.getDoYouKnowContentData().toDomain()
}

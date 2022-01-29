package com.deneyehayir.deneysiz.data.repository

import com.deneyehayir.deneysiz.data.local.datasource.AssetDataSource
import com.deneyehayir.deneysiz.data.local.model.toDomain
import com.deneyehayir.deneysiz.data.remote.datasource.RemoteDataSource
import com.deneyehayir.deneysiz.data.remote.model.request.BrandDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.response.toDomain
import com.deneyehayir.deneysiz.domain.model.BrandDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificatesDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val assetDataSource: AssetDataSource
) : Repository {

    override suspend fun fetchCategories(): CategoryDomainModel =
        remoteDataSource.fetchCategories().toDomain()

    override suspend fun fetchCategoryDetail(
        categoryId: String
    ): CategoryDetailDomainModel = remoteDataSource.fetchCategoryDetail(
        requestBody = CategoryDetailRequestBody(
            categoryId = categoryId
        )
    ).toDomain()

    override suspend fun fetchBrandDetail(brandId: Int): BrandDetailDomainModel =
        remoteDataSource.fetchBrandDetail(
            requestBody = BrandDetailRequestBody(
                brandId = brandId
            )
        ).toDomain()

    override suspend fun fetchCertificates(): CertificatesDomainModel =
        assetDataSource.getCertificates().toDomain()
}

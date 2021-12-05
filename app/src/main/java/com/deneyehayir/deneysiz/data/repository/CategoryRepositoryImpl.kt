package com.deneyehayir.deneysiz.data.repository

import com.deneyehayir.deneysiz.data.remote.datasource.CategoryRemoteDataSource
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.toDomain
import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: CategoryRemoteDataSource
) : CategoryRepository {

    override suspend fun fetchCategories(): CategoryDomainModel =
        remoteDataSource.fetchCategories().toDomain()

    override suspend fun fetchCategoryDetail(
        categoryId: String
    ): CategoryDetailDomainModel = remoteDataSource.fetchCategoryDetail(
        requestBody = CategoryDetailRequestBody(
            categoryId = categoryId
        )
    ).toDomain()
}

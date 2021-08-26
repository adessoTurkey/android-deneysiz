package com.deneyehayir.deneysiz.data.repository

import com.deneyehayir.deneysiz.data.remote.datasource.CategoryRemoteDataSource
import com.deneyehayir.deneysiz.data.remote.model.CategoryDetailResponse
import com.deneyehayir.deneysiz.data.remote.model.CategoryResponse
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val remoteDataSource: CategoryRemoteDataSource
) {

    suspend fun fetchCategories(): CategoryResponse = remoteDataSource.fetchCategories()

    suspend fun fetchCategoryDetail(
        categoryId: String
    ): CategoryDetailResponse = remoteDataSource.fetchCategoryDetail(
        requestBody = CategoryDetailRequestBody(
            categoryId = categoryId
        )
    )
}

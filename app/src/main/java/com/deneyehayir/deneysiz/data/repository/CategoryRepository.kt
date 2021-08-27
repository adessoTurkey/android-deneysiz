package com.deneyehayir.deneysiz.data.repository

import com.deneyehayir.deneysiz.data.remote.datasource.CategoryRemoteDataSource
import com.deneyehayir.deneysiz.data.remote.model.CategoryResponse
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val remoteDataSource: CategoryRemoteDataSource
) {

    suspend fun fetchCategories(): CategoryResponse = remoteDataSource.fetchCategories()
}

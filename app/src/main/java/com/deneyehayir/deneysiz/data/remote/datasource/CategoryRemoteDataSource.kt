package com.deneyehayir.deneysiz.data.remote.datasource

import com.deneyehayir.deneysiz.data.remote.BaseRemoteDataSource
import com.deneyehayir.deneysiz.data.remote.api.CategoryService
import com.deneyehayir.deneysiz.data.remote.model.CategoryResponse
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(
    private val service: CategoryService
) : BaseRemoteDataSource() {

    suspend fun fetchCategories(): CategoryResponse = invoke { service.fetchCategories() }
}

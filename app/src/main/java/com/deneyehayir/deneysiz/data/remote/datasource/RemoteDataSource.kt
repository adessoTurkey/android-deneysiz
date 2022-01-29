package com.deneyehayir.deneysiz.data.remote.datasource

import com.deneyehayir.deneysiz.data.remote.BaseRemoteDataSource
import com.deneyehayir.deneysiz.data.remote.api.ApiService
import com.deneyehayir.deneysiz.data.remote.model.request.BrandDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.response.BrandDetailResponse
import com.deneyehayir.deneysiz.data.remote.model.response.CategoryDetailResponse
import com.deneyehayir.deneysiz.data.remote.model.response.CategoryResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: ApiService
) : BaseRemoteDataSource() {

    suspend fun fetchCategories(): CategoryResponse = invoke { service.fetchCategories() }

    suspend fun fetchCategoryDetail(
        requestBody: CategoryDetailRequestBody
    ): CategoryDetailResponse = invoke {
        service.fetchCategoryDetail(
            requestBody = requestBody
        )
    }

    suspend fun fetchBrandDetail(
        requestBody: BrandDetailRequestBody
    ): BrandDetailResponse = invoke {
        service.fetchBrandDetail(
            requestBody = requestBody
        )
    }
}

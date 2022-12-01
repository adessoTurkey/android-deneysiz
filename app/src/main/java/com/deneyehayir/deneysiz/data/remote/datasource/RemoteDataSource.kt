package com.deneyehayir.deneysiz.data.remote.datasource

import com.deneyehayir.deneysiz.data.remote.BaseRemoteDataSource
import com.deneyehayir.deneysiz.data.remote.api.ApiService
import com.deneyehayir.deneysiz.data.remote.model.request.BrandDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.SearchBrandsRequestBody
import com.deneyehayir.deneysiz.data.remote.model.response.BrandByCategoryResponse
import com.deneyehayir.deneysiz.data.remote.model.response.SearchBrandsResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: ApiService
) : BaseRemoteDataSource() {

    suspend fun fetchCategoryDetail(
        requestBody: CategoryDetailRequestBody
    ): BrandByCategoryResponse = invoke {
        service.fetchCategoryDetail(
            requestBody = requestBody
        )
    }

    suspend fun fetchBrandDetail(
        requestBody: BrandDetailRequestBody
    ): BrandByCategoryResponse = invoke {
        service.fetchBrandDetail(
            requestBody = requestBody
        )
    }

    suspend fun fetchSearchResult(
        requestBody: SearchBrandsRequestBody
    ): SearchBrandsResponse = invoke {
        service.fetchSearchResult(
            requestBody = requestBody
        )
    }
}

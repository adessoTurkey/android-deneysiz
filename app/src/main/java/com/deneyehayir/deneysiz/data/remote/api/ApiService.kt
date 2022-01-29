package com.deneyehayir.deneysiz.data.remote.api

import com.deneyehayir.deneysiz.data.remote.model.request.BrandDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.response.BrandDetailResponse
import com.deneyehayir.deneysiz.data.remote.model.response.CategoryDetailResponse
import com.deneyehayir.deneysiz.data.remote.model.response.CategoryResponse
import com.deneyehayir.deneysiz.internal.util.api.LocalRequestType
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Tag

interface ApiService {

    @GET(CATEGORIES)
    suspend fun fetchCategories(
        @Tag localRequestType: LocalRequestType = LocalRequestType.CATEGORIES
    ): CategoryResponse

    @POST(CATEGORY_DETAIL)
    suspend fun fetchCategoryDetail(
        @Tag localRequestType: LocalRequestType = LocalRequestType.CATEGORY_DETAIL,
        @Body requestBody: CategoryDetailRequestBody
    ): CategoryDetailResponse

    @POST(BRANDS_DETAIL)
    suspend fun fetchBrandDetail(
        @Tag localRequestType: LocalRequestType = LocalRequestType.BRANDS_DETAIL,
        @Body requestBody: BrandDetailRequestBody
    ): BrandDetailResponse

    companion object {
        const val CATEGORIES = "remote/response_data_categories.json"
        const val CATEGORY_DETAIL = "remote/response_category_detail.json"
        const val BRANDS_DETAIL = "remote/response_brands_detail.json"
    }
}

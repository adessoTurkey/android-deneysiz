package com.deneyehayir.deneysiz.data.remote.api

import com.deneyehayir.deneysiz.BuildConfig
import com.deneyehayir.deneysiz.data.remote.model.request.BrandDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import com.deneyehayir.deneysiz.data.remote.model.request.SearchBrandsRequestBody
import com.deneyehayir.deneysiz.data.remote.model.response.BrandByCategoryResponse
import com.deneyehayir.deneysiz.data.remote.model.response.SearchBrandsResponse
import com.deneyehayir.deneysiz.internal.util.api.LocalRequestType
import com.deneyehayir.deneysiz.internal.util.api.LocalRequestType.LOCAL_BRANDS_DETAIL
import com.deneyehayir.deneysiz.internal.util.api.LocalRequestType.LOCAL_CATEGORY_DETAIL
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Tag

interface ApiService {

    @POST(CATEGORY_DETAIL)
    suspend fun fetchCategoryDetail(
        @Tag type: LocalRequestType? = if (BuildConfig.DEBUG) LOCAL_CATEGORY_DETAIL else null,
        @Body requestBody: CategoryDetailRequestBody
    ): BrandByCategoryResponse

    @POST(BRANDS_DETAIL)
    suspend fun fetchBrandDetail(
        @Tag type: LocalRequestType? = if (BuildConfig.DEBUG) LOCAL_BRANDS_DETAIL else null,
        @Body requestBody: BrandDetailRequestBody
    ): BrandByCategoryResponse

    @POST(SEARCH_BRAND)
    suspend fun fetchSearchResult(
        @Tag type: LocalRequestType? = null,
        @Body requestBody: SearchBrandsRequestBody
    ): SearchBrandsResponse

    companion object {
        const val CATEGORY_DETAIL = "brands/byCategory"
        const val BRANDS_DETAIL = "brands/detail"
        const val SEARCH_BRAND = "brands/search"
    }
}

package com.deneyehayir.deneysiz.data.remote.api

import com.deneyehayir.deneysiz.data.remote.model.CategoryDetailResponse
import com.deneyehayir.deneysiz.data.remote.model.CategoryResponse
import com.deneyehayir.deneysiz.data.remote.model.request.CategoryDetailRequestBody
import com.deneyehayir.deneysiz.internal.util.api.LocalRequestType
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Tag

interface CategoryService {

    @GET(CATEGORIES)
    suspend fun fetchCategories(
        @Tag localRequestType: LocalRequestType = LocalRequestType.CATEGORIES
    ): CategoryResponse

    @POST(CATEGORY_DETAIL)
    suspend fun fetchCategoryDetail(
        @Tag localRequestType: LocalRequestType = LocalRequestType.CATEGORY_DETAIL,
        @Body requestBody: CategoryDetailRequestBody
    ): CategoryDetailResponse

    companion object {
        const val CATEGORIES = "local/response_data_categories.json"
        const val CATEGORY_DETAIL = "local/response_category_detail.json"
    }
}

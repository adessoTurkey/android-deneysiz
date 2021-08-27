package com.deneyehayir.deneysiz.data.remote.api

import com.deneyehayir.deneysiz.data.remote.model.CategoryResponse
import com.deneyehayir.deneysiz.internal.util.api.LocalRequestType
import retrofit2.http.GET
import retrofit2.http.Tag

interface CategoryService {

    @GET(CATEGORIES)
    suspend fun fetchCategories(
        @Tag localRequestType: LocalRequestType = LocalRequestType.CATEGORIES
    ): CategoryResponse

    companion object {
        const val CATEGORIES = "local/response_data_categories.json"
    }
}

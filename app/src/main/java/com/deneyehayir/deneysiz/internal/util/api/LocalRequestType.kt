package com.deneyehayir.deneysiz.internal.util.api

import com.deneyehayir.deneysiz.data.remote.api.ApiService
import okhttp3.Request

enum class LocalRequestType(val endpoint: String) {
    CATEGORIES(ApiService.CATEGORIES),
    CATEGORY_DETAIL(ApiService.CATEGORY_DETAIL),
    BRANDS_DETAIL(ApiService.BRANDS_DETAIL),
    NONE("none");

    companion object {
        fun fromRequest(request: Request): LocalRequestType =
            request.tag(LocalRequestType::class.java) ?: NONE
    }
}

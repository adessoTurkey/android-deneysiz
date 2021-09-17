package com.deneyehayir.deneysiz.internal.util.api

import com.deneyehayir.deneysiz.data.remote.api.CategoryService
import okhttp3.Request

enum class LocalRequestType(val endpoint: String) {
    CATEGORIES(CategoryService.CATEGORIES),
    CATEGORY_DETAIL(CategoryService.CATEGORY_DETAIL),
    NONE("none");

    companion object {
        fun fromRequest(request: Request): LocalRequestType =
            request.tag(LocalRequestType::class.java) ?: NONE
    }
}

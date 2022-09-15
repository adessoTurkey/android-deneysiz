package com.deneyehayir.deneysiz.internal.util.api

import okhttp3.Request

private const val categoryDetail = "remote/response_category_detail.json"
private const val brandsDetail = "remote/response_brands_detail.json"

enum class LocalRequestType(val endpoint: String) {
    LOCAL_CATEGORY_DETAIL(categoryDetail),
    LOCAL_BRANDS_DETAIL(brandsDetail)
}

fun Request.isLocalRequestTagAvailable(): LocalRequestType? = tag(LocalRequestType::class.java)

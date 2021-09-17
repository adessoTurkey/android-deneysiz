package com.deneyehayir.deneysiz.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDetailRequestBody(
    val categoryId: String
)

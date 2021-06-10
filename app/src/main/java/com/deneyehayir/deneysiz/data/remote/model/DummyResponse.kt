package com.deneyehayir.deneysiz.data.remote.model

import com.deneyehayir.deneysiz.data.remote.BaseResponseModel
import kotlinx.serialization.Serializable

@Serializable
data class DummyResponse(
    val id: String
) : BaseResponseModel()

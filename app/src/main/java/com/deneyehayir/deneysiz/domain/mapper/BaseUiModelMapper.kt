package com.deneyehayir.deneysiz.domain.mapper

import com.deneyehayir.deneysiz.data.remote.BaseResponseModel
import com.deneyehayir.deneysiz.domain.model.BaseUiModel

interface BaseUiModelMapper<UM : BaseUiModel, RM : BaseResponseModel> {
    fun mapToUiModel(response: RM): UM
}

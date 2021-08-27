package com.deneyehayir.deneysiz.domain.mapper

import com.deneyehayir.deneysiz.domain.model.UiModel

/**
 * Convention for transforming DataSourceModel to UiModel
 */
interface UiModelMapper<UM : UiModel, DSM> {
    fun mapToUiModel(response: DSM): UM
}

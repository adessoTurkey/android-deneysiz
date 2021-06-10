package com.deneyehayir.deneysiz.domain.mapper

import com.deneyehayir.deneysiz.data.remote.model.DummyResponse
import com.deneyehayir.deneysiz.domain.model.DummyUiModel
import javax.inject.Inject

class DummyUiModelMapper @Inject constructor() : BaseUiModelMapper<DummyUiModel, DummyResponse> {

    override fun mapToUiModel(response: DummyResponse) = with(response) {
        DummyUiModel(
            dummyId = id
        )
    }
}

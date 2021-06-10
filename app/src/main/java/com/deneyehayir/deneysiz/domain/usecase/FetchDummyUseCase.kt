package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.data.remote.model.DummyResponse
import com.deneyehayir.deneysiz.data.repository.DummyRepository
import com.deneyehayir.deneysiz.domain.mapper.DummyUiModelMapper
import com.deneyehayir.deneysiz.domain.model.DummyUiModel
import javax.inject.Inject

class FetchDummyUseCase @Inject constructor(
    private val repository: DummyRepository,
    private val mapper: DummyUiModelMapper
) : UseCase<DummyResponse, DummyUiModel, FetchDummyUseCase.Params>() {

    override suspend fun buildUseCase(params: Params): DummyResponse = repository.fetchDummy()

    override fun map(transformType: DummyResponse): DummyUiModel =
        mapper.mapToUiModel(transformType)

    data class Params(
        val queryParam: String
    )
}

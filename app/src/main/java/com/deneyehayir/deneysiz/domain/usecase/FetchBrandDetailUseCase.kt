package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.BrandDetailDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchBrandDetailUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<BrandDetailDomainModel, FetchBrandDetailUseCase.Params>(dispatcher) {

    override suspend fun execute(params: Params): BrandDetailDomainModel =
        repository.fetchBrandDetail(
            brandId = params.brandId
        )

    data class Params(
        val brandId: Int
    )
}

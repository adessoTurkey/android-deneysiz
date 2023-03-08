package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoveBrandFromFollowingUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : UseCase<Unit, RemoveBrandFromFollowingUseCase.Params>(dispatcher) {

    override suspend fun execute(params: Params) {
        repository.removeBrandFromFollowing(params.brandId)
    }

    data class Params(
        val brandId: Int
    )
}

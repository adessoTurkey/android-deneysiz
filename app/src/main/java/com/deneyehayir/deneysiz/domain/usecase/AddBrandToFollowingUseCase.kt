package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddBrandToFollowingUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : UseCase<Long, AddBrandToFollowingUseCase.Params>(dispatcher) {

    override suspend fun execute(params: Params): Long =
        repository.addBrandToFollowing(params.brand)

    data class Params(
        val brand: CategoryDetailItemDomainModel
    )
}

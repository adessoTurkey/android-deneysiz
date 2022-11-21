package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchAllFollowingBrandsUseCase @Inject constructor(
    private val repository: Repository, @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<List<CategoryDetailItemDomainModel>, FetchAllFollowingBrandsUseCase.Params>(dispatcher) {

    override suspend fun execute(params: Params): List<CategoryDetailItemDomainModel> =
        repository.fetchFollowingBrands()

    object Params

}

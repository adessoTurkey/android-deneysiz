package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.WhoWeAreDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchWhoWeAreDataUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<WhoWeAreDomainModel, Unit>(dispatcher) {

    override suspend fun execute(params: Unit): WhoWeAreDomainModel =
        repository.fetchWhoWeAreData()
}

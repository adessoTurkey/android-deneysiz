package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.SupportDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchSupportDataUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<SupportDomainModel, Unit>(dispatcher) {

    override suspend fun execute(params: Unit): SupportDomainModel =
        repository.fetchSupportData()
}

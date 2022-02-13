package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.DonationDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchDonationDataUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<DonationDomainModel, Unit>(dispatcher) {

    override suspend fun execute(params: Unit): DonationDomainModel =
        repository.fetchDonationData()
}

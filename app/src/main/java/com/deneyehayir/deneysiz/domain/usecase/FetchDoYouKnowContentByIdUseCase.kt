package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.DoYouKnowContentDetailDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchDoYouKnowContentByIdUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<DoYouKnowContentDetailDomainModel,
    FetchDoYouKnowContentByIdUseCase.Params>(dispatcher) {

    override suspend fun execute(params: Params): DoYouKnowContentDetailDomainModel {
        return repository.fetchDoYouKnowContentData().contentDetails.firstOrNull { content ->
            content.id == params.id
        } ?: DoYouKnowContentDetailDomainModel.EMPTY
    }

    data class Params(
        val id: Int
    )
}

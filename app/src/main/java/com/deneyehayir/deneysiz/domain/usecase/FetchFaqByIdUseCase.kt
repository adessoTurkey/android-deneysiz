package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.FaqItemDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchFaqByIdUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<FaqItemDomainModel, FetchFaqByIdUseCase.Params>(dispatcher) {

    override suspend fun execute(params: Params): FaqItemDomainModel {
        return repository.fetchDoYouKnowData().faqList.firstOrNull { faq ->
            faq.id == params.id
        } ?: FaqItemDomainModel.Empty
    }

    data class Params(
        val id: Int
    )
}

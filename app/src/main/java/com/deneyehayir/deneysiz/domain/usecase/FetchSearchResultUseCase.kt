package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.SearchResultDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSearchResultUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : FlowUseCase<FetchSearchResultUseCase.Params, SearchResultDomainModel>(dispatcher) {

    override suspend fun execute(params: Params): Flow<SearchResultDomainModel> = invoke {
        repository.fetchSearchResult(params.query)
    }

    data class Params(
        val query: String
    )
}

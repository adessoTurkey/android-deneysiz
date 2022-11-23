package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.SearchResultDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchSearchResultUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : FlowUseCase<FetchSearchResultUseCase.Params, SearchResultDomainModel>() {

    override suspend fun execute(params: Params): Flow<SearchResultDomainModel> {
        return flow {
            emit(repository.fetchSearchResult(params.query))
        }.flowOn(dispatcher)
    }

    data class Params(
        val query: String
    )
}

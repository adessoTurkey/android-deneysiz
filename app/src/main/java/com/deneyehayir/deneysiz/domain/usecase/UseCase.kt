package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.data.remote.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<DataSourceType, DomainType, Params> {

    protected abstract suspend fun buildUseCase(params: Params): DataSourceType

    protected abstract fun map(dataSourceType: DataSourceType): DomainType

    suspend operator fun invoke(params: Params): Result<DomainType> =
        withContext(Dispatchers.IO) {
            try {
                Result.success(buildUseCase(params))
                    .map { map(it) }
            } catch (failure: Failure) {
                Result.failure(failure)
            }
        }
}

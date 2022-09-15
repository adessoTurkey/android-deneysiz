package com.deneyehayir.deneysiz.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<T, in Params>(
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(params: Params): Result<T> = withContext(dispatcher) {
        runCatching { execute(params) }
    }

    protected abstract suspend fun execute(params: Params): T
}

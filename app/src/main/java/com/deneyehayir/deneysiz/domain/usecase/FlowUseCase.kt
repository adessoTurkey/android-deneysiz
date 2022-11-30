package com.deneyehayir.deneysiz.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, out T : Any>(
    private val dispatcher: CoroutineDispatcher
) {
    abstract suspend fun execute(params: Params): Flow<T>

    protected suspend operator fun <T> invoke(block: suspend () -> T): Flow<T> = flow {
        emit(block.invoke())
    }.flowOn(dispatcher)
}

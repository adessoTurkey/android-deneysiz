package com.deneyehayir.deneysiz.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in Params, out T : Any> {
    abstract suspend fun execute(params: Params): Flow<T>
}

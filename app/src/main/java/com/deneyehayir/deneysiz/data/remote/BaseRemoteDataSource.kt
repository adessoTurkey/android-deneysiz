package com.deneyehayir.deneysiz.data.remote

import kotlinx.serialization.SerializationException

open class BaseRemoteDataSource {

    suspend fun <O> invoke(serviceFunction: suspend () -> O): O {
        return try {
            serviceFunction()
        } catch (exception: Exception) {
            throw asFailure(exception)
        }
    }

    private fun asFailure(exception: Exception): Failure {
        return when (exception) {
            is Failure -> exception
            is SerializationException -> JsonError
            else -> UnknownError(exception)
        }
    }
}

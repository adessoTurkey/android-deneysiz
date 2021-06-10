package com.deneyehayir.deneysiz.data.remote

import java.io.IOException

sealed class Failure : IOException()

data class ServerError(val code: Int, override val message: String?) : Failure()
data class UnAuthError(override val message: String) : Failure()
data class ApiError(
    val code: Int,
    override val message: String,
    val errorCode: String? = null
) : Failure()
data class UnknownError(val throwable: Throwable) : Failure()
data class HttpError(val code: Int, override val message: String) : Failure()
data class TimeOutError(override val message: String?) : Failure()
object JsonError : Failure()
object UnknownHostError : Failure()
object NoConnectivityError : Failure()
object EmptyResponse : Failure()
object IgnorableError : Failure()

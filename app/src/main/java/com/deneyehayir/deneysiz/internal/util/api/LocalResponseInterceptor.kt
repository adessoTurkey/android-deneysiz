package com.deneyehayir.deneysiz.internal.util.api

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

/**
 * Acts as if the api request's response is delivered from a remote source.
 * Intercepts the api request and if request's tag contains any [LocalRequestType],
 * delivers a response model by reading a given json file.
 */
class LocalResponseInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain.request()) {
            when (val type = this.isLocalRequestTagAvailable()) {
                is LocalRequestType -> createLocalResponse(this, type.endpoint)
                else -> chain.proceed(this)
            }
        }
    }

    private fun createLocalResponse(request: Request, filePath: String): Response {
        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_2)
            .message("OK")
            .body(
                readFromJson(filePath).toResponseBody("application/json".toMediaType())
            )
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun readFromJson(filePath: String): ByteArray =
        context.resources
            .assets
            .open(filePath)
            .readBytes()
}

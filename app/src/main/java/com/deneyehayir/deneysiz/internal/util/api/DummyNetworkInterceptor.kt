package com.deneyehayir.deneysiz.internal.util.api

import com.deneyehayir.deneysiz.data.remote.model.DummyResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

// credits: https://github.com/googlecodelabs/kotlin-coroutines/ SkipNetworkInterceptor
class DummyNetworkInterceptor : Interceptor {
    private var attempts = 1

    /**
     * Return true iff this request should error.
     */
    private fun wantRandomError() = attempts++ % 5 == 0

    /**
     * Stop the request from actually going out to the network.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        pretendToBlockForNetworkRequest()
        return if (wantRandomError()) {
            makeErrorResult(chain.request())
        } else {
            makeOkResult(chain.request())
        }
    }

    /**
     * Pretend to "block" interacting with the network.
     *
     * Really: sleep for 500ms.
     */
    private fun pretendToBlockForNetworkRequest() = Thread.sleep(500)

    /**
     * Generate an error result.
     *
     * ```
     * HTTP/1.1 500 Bad server day
     * Content-type: application/json
     *
     * {"cause": "not sure"}
     * ```
     */
    private fun makeErrorResult(request: Request): Response {
        return Response.Builder()
            .code(500)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("Bad server day")
            .body(
                Json.encodeToString(
                    mapOf("cause" to "not sure")
                ).toResponseBody(
                    "application/json".toMediaType()
                )
            )
            .build()
    }

    /**
     * Generate a success response.
     *
     * ```
     * HTTP/1.1 200 OK
     * Content-type: application/json
     *
     * "{id:123}"
     * ```
     */
    private fun makeOkResult(request: Request): Response {
        val dummyResponse = DummyResponse("123")
        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("OK")
            .body(
                Json.encodeToString(dummyResponse)
                    .toResponseBody(
                        "application/json".toMediaType()
                    )
            )
            .build()
    }
}

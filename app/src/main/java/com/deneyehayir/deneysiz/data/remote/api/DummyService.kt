package com.deneyehayir.deneysiz.data.remote.api

import com.deneyehayir.deneysiz.data.remote.model.DummyResponse
import retrofit2.http.GET

interface DummyService {

    @GET(DUMMY_ENDPOINT)
    suspend fun fetchDummy(): DummyResponse

    companion object {
        const val DUMMY_ENDPOINT = "v1/data/dummy"
    }
}

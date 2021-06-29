package com.deneyehayir.deneysiz.data.remote.datasource

import com.deneyehayir.deneysiz.data.remote.BaseRemoteDataSource
import com.deneyehayir.deneysiz.data.remote.api.DummyService
import javax.inject.Inject

class DummyRemoteDataSource @Inject constructor(
    private val service: DummyService
) : BaseRemoteDataSource() {

    suspend fun fetchDummy() = invoke { service.fetchDummy() }
}

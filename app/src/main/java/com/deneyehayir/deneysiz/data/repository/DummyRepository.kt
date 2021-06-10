package com.deneyehayir.deneysiz.data.repository

import com.deneyehayir.deneysiz.data.remote.datasource.DummyRemoteDataSource
import com.deneyehayir.deneysiz.data.remote.model.DummyResponse
import javax.inject.Inject

class DummyRepository @Inject constructor(
    private val remoteDataSource: DummyRemoteDataSource
) {

    suspend fun fetchDummy(): DummyResponse = remoteDataSource.fetchDummy()
}

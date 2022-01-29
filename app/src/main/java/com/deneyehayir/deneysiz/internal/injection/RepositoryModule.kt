package com.deneyehayir.deneysiz.internal.injection

import com.deneyehayir.deneysiz.data.repository.RepositoryImpl
import com.deneyehayir.deneysiz.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(repository: RepositoryImpl): Repository
}

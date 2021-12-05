package com.deneyehayir.deneysiz.internal.injection

import com.deneyehayir.deneysiz.data.repository.CategoryRepositoryImpl
import com.deneyehayir.deneysiz.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository
}

package com.deneyehayir.deneysiz.internal.injection

import android.content.Context
import androidx.room.Room
import com.deneyehayir.deneysiz.data.local.database.DeneysizDatabase
import com.deneyehayir.deneysiz.data.local.database.dao.BrandsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDeneysizDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DeneysizDatabase::class.java,
        "database_deneysiz"
    ).build()

    @Singleton
    @Provides
    fun provideBrandsDao(deneysizDatabase: DeneysizDatabase): BrandsDao {
        return deneysizDatabase.brandsDao()
    }
}

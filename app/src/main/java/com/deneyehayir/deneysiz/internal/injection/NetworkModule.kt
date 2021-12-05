package com.deneyehayir.deneysiz.internal.injection

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.deneyehayir.deneysiz.BuildConfig
import com.deneyehayir.deneysiz.data.remote.api.ApiService
import com.deneyehayir.deneysiz.internal.util.api.ErrorHandlingInterceptor
import com.deneyehayir.deneysiz.internal.util.api.LocalResponseInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideChuckerInterceptor(
        @ApplicationContext context: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        errorHandlingInterceptor: ErrorHandlingInterceptor,
        localResponseInterceptor: LocalResponseInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(errorHandlingInterceptor)
            .addInterceptor(localResponseInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    internal fun provideRetrofit(
        client: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create()
}

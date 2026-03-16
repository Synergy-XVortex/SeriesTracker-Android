package com.example.seriestracker.di

import com.example.seriestracker.data.remote.api.EpisodateApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Module to tell Hilt how to provide network dependencies
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Base URL for the EpisoDate API
    // The trailing slash is mandatory for Retrofit
    private const val BASE_URL = "https://www.episodate.com/api/"

    // Provides the OkHttpClient with a logging interceptor to see network calls in Logcat
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // Provides the Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // Gson to parse JSON
            .build()
    }

    // Provides the API service interface
    @Provides
    @Singleton
    fun provideEpisodateApiService(retrofit: Retrofit): EpisodateApiService {
        return retrofit.create(EpisodateApiService::class.java)
    }
}
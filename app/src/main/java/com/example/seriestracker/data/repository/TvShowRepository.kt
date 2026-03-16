package com.example.seriestracker.data.repository

import com.example.seriestracker.data.mapper.toDomain
import com.example.seriestracker.data.remote.api.EpisodateApiService
import com.example.seriestracker.domain.model.TvShow
import javax.inject.Inject
import javax.inject.Singleton

// Repository class responsible for data handling
// @Singleton ensures only one instance of this repository exists in the app
@Singleton
class TvShowRepository @Inject constructor(
    private val apiService: EpisodateApiService
) {

    // Fetches popular TV shows and wraps the result in Kotlin's built-in Result class
    // to handle success and failure gracefully
    suspend fun getPopularShows(page: Int = 1): Result<List<TvShow>> {
        return try {
            // 1. Fetch the raw data (DTO) from the API
            val response = apiService.getPopularShows(page)

            // 2. Convert the list of DTOs into a list of clean Domain models
            val tvShows = response.tvShows.toDomain()

            // 3. Return a successful result containing the clean data
            Result.success(tvShows)

        } catch (exception: Exception) {
            // 4. Catch any network or parsing errors and return a failure result
            Result.failure(exception)
        }
    }
}
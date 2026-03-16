package com.example.seriestracker.data.remote.api

import com.example.seriestracker.data.remote.dto.PopularResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit interface to define the API endpoints
interface EpisodateApiService {

    // Fetches the most popular TV shows
    // The @Query annotation appends "?page=X" to the URL
    @GET("most-popular")
    suspend fun getPopularShows(
        @Query("page") page: Int = 1
    ): PopularResponseDto
}
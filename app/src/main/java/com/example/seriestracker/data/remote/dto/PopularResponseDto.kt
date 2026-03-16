package com.example.seriestracker.data.remote.dto

import com.google.gson.annotations.SerializedName

// DTO representing the root JSON response for popular shows
data class PopularResponseDto(
    @SerializedName("total") val total: String,
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("tv_shows") val tvShows: List<TvShowDto>
)
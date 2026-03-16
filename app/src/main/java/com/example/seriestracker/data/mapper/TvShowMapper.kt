package com.example.seriestracker.data.mapper

import com.example.seriestracker.data.remote.dto.TvShowDto
import com.example.seriestracker.domain.model.TvShow

// Extension function to convert DTO to Domain model
fun TvShowDto.toDomain(): TvShow {
    return TvShow(
        id = this.id,
        name = this.name,
        // Providing default fallback values if the API returns null
        network = this.network ?: "Unknown Network",
        country = this.country ?: "Unknown Country",
        status = this.status ?: "Unknown Status",
        imageUrl = this.imageThumbnailPath ?: ""
    )
}

// Extension to map a list of DTOs to a list of Domain models
fun List<TvShowDto>.toDomain(): List<TvShow> {
    return this.map { it.toDomain() }
}
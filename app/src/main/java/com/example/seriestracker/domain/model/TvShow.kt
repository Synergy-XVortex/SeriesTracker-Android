package com.example.seriestracker.domain.model

// Clean domain model used by the UI
data class TvShow(
    val id: Int,
    val name: String,
    val network: String,
    val country: String,
    val status: String,
    val imageUrl: String
)
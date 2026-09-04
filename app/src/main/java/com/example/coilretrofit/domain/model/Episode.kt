package com.example.coilretrofit.domain.model

data class Episode(
    val id: Int,
    val episodeName: String,
    val episodeNumber: String,
    val releaseData: String,
    val charactersInEpisodeCount: Int
)

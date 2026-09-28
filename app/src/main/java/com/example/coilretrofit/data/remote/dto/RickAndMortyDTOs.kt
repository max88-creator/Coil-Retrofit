package com.example.coilretrofit.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ReferenceDTO(
    val name: String,
    val url: String
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: ReferenceDTO,
    val location: ReferenceDTO,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
)

data class EpisodeDto(
    val id: Int,
    val name: String,
    @SerializedName("air_date") val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
)

data class PaginateInfoDto(
    val count: Int,
    val pages: Int,
    val nextPage: String?,
    val prevPage: String?
)

data class PaginateResponseDto<T>(
    val info: PaginateInfoDto,
    val results: List<T>
)
package com.example.coilretrofit.data.remote.api

import com.example.coilretrofit.data.remote.dto.CharacterDto
import com.example.coilretrofit.data.remote.dto.EpisodeDto
import com.example.coilretrofit.data.remote.dto.LocationDto
import com.example.coilretrofit.data.remote.dto.PaginateResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): PaginateResponseDto<CharacterDto>
    @GET("character")
    suspend fun searchCharacters(
        @Query("name") name: String,
        @Query("page") page: Int
    ): PaginateResponseDto<CharacterDto>
    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDto
    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int
    ): PaginateResponseDto<LocationDto>
    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): PaginateResponseDto<EpisodeDto>
}
package com.example.coilretrofit.data.mapper

import coil.map.Mapper
import com.example.coilretrofit.data.local.entity.FavoriteCharacterEntity
import com.example.coilretrofit.data.remote.dto.CharacterDto
import com.example.coilretrofit.data.remote.dto.EpisodeDto
import com.example.coilretrofit.data.remote.dto.LocationDto
import com.example.coilretrofit.data.remote.dto.PaginateInfoDto
import com.example.coilretrofit.data.remote.dto.PaginateResponseDto
import com.example.coilretrofit.data.remote.dto.ReferenceDTO
import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.model.CharacterStatus
import com.example.coilretrofit.domain.model.Episode
import com.example.coilretrofit.domain.model.Location
import com.example.coilretrofit.domain.model.PaginatedResult

fun CharacterDto.toDomain(): Character = Character(
    id = id,
    name = name,
    status = CharacterStatus.fromApiValue(status),
    species = species,
    type = type.ifBlank { "--" },
    gender = gender,
    originName = origin.name,
    location = location.url,
    episodesCount = episode.size,
    imageUrl = image
)

fun LocationDto.toDomain(): Location = Location(
    id = id,
    placeName = name,
    type = type.ifBlank { "--" },
    dimension = dimension,
    residentsCount = residents.size
)

fun EpisodeDto.toDomain(): Episode = Episode(
    id = id,
    episodeName = name,
    episodeNumber = episode,
    releaseData = airDate,
    charactersInEpisodeCount = characters.size
)

fun <R> PaginateInfoDto.toDomain(page: Int, items: List<R>):
        PaginatedResult<R> =
    PaginatedResult(
        items = items,
        currentPage = page,
        hasNextPage = nextPage != null,
        totalPages = pages
    )

fun <T, R> PaginateResponseDto<T>.toDomain(
    page: Int,
    mapper: (T) -> R
): PaginatedResult<R> =
    info.toDomain(
        page,
        items = results.map(mapper)
    )

fun Character.toFavoriteEntity(): FavoriteCharacterEntity =
    FavoriteCharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        originName = originName,
        location = location,
        episodesCount = episodesCount,
        imageUrl = imageUrl
    )

fun FavoriteCharacterEntity.toDomain(): Character = Character(
    id = id,
    name = name,
    status = CharacterStatus.fromApiValue(status.toString()),
    species = species,
    type = type,
    gender = gender,
    originName = originName,
    imageUrl = imageUrl,
    location = location,
    episodesCount = episodesCount,
)
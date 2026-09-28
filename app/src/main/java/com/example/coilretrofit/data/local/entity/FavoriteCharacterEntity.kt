package com.example.coilretrofit.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coilretrofit.domain.model.CharacterStatus
@Entity(tableName = "favorites")
data class FavoriteCharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: String,
    val originName: String,
    val location: String,
    val episodesCount: Int,
    val imageUrl: String
)

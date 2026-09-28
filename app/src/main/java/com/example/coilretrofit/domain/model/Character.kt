package com.example.coilretrofit.domain.model

import com.google.devtools.ksp.symbol.Origin

data class Character(
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

enum class CharacterStatus {
    ALIVE,
    DEAD,
    UNKNOWN;

    companion object {
        fun fromApiValue(value: String): CharacterStatus =
            when (value.lowercase()) {
                "alive" -> ALIVE
                "dead" -> DEAD
                else -> UNKNOWN
            }
    }
}
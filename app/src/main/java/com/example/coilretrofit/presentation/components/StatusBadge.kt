package com.example.coilretrofit.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.coilretrofit.domain.model.CharacterStatus
import com.example.coilretrofit.ui.theme.StatusAlive
import com.example.coilretrofit.ui.theme.StatusDead
import com.example.coilretrofit.ui.theme.StatusUnknown

@Composable
fun StatusBadge(
    characterStatus: CharacterStatus
) {
    val color = when(characterStatus) {
        CharacterStatus.DEAD -> {
            StatusDead
        }
        CharacterStatus.ALIVE -> {
            StatusAlive
        }
        CharacterStatus.UNKNOWN -> {
            StatusUnknown
        }
    }
    Text(
        text = characterStatus.name,
        fontSize = 18.sp,
        color = color
    )
}

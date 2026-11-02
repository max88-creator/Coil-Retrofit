package com.example.coilretrofit.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.presentation.event.displayName

@Composable
fun CharacterDetailContent(
    character: Character
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
        )
        DetailRoute(
            label = "Status",
            value = character.status.displayName()
        )
        DetailRoute(
            label = "Species",
            value = character.species
        )
        DetailRoute(
            label = "Type",
            value = character.type
        )
        DetailRoute(
            label = "Gender",
            value = character.gender
        )
        DetailRoute(
            label = "Origin",
            value = character.originName
        )
        DetailRoute(
            label = "Location",
            value = character.location
        )
        DetailRoute(
            label = "Episodes",
            value = character.episodesCount.toString()
        )
    }
}
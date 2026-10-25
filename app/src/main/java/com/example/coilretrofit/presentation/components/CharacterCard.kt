package com.example.coilretrofit.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.coilretrofit.R
import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.ui.theme.PortalGreen

@Composable
fun CharacterCard(
    character: Character,
    onDetailsScreenClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp)),
        onClick = {
            onDetailsScreenClick()
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = character.name,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(4.dp))
                StatusBadge(
                    characterStatus = character.status
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${character.species} · ${character.location}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            IconButton(
                onClick = {
                    onFavoriteClick()
                }
            ) {
                Icon(
                    painter = painterResource(if (isFavorite) {
                        R.drawable.baseline_favorite
                    } else {
                        R.drawable.outline_favorite
                    }),
                    contentDescription = null,
                    tint = if (isFavorite) PortalGreen else MaterialTheme.colorScheme.onSurfaceVariant
                )
//                Icon(
//                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
//                    contentDescription = null,
//                    tint = if (isFavorite) PortalGreen else MaterialTheme.colorScheme.onSurfaceVariant,
//                )
            }
        }
    }
}
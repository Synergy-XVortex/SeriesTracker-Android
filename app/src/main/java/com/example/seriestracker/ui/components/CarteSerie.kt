package com.example.seriestracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.seriestracker.domain.model.TvShow

@Composable
fun CarteSerie(
    tvShow: TvShow,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. The Image loaded from the network using Coil
            AsyncImage(
                model = tvShow.imageUrl,
                contentDescription = "Poster of ${tvShow.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp, 120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray) // Placeholder color while loading
            )

            Spacer(modifier = Modifier.width(16.dp))

            // 2. The Text Information
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = tvShow.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${tvShow.network} • ${tvShow.country}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))

                // 3. The Status Badge
                BadgeStatut(status = tvShow.status)
            }
        }
    }
}

// Small helper Composable for the status badge (Green for Running, Gray for Ended)
@Composable
fun BadgeStatut(status: String) {
    val backgroundColor = if (status.equals("Running", ignoreCase = true)) {
        Color(0xFF4CAF50) // Green
    } else {
        Color.Gray
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = status,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
    }
}
package io.xavier.topwsb.presentation.common_composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.xavier.topwsb.domain.model.Sentiment

/**
 * Custom composable for displaying sentiment within a card with a background that is specified
 * in [Sentiment]. If [sentiment] is [Sentiment.UNKNOWN], the background of the card will be set
 * to [unknownBg]
 *
 * @param sentiment [Sentiment] to display in the card
 * @param modifier [Modifier]
 * @param unknownBg [Color] that is set as the card's background if [sentiment] is
 *  [Sentiment.UNKNOWN]
 */
@Composable
fun SentimentCard(
    sentiment: Sentiment,
    modifier: Modifier = Modifier,
    unknownBg: Color
) {
    Card(
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(
            containerColor = sentiment.color ?: unknownBg,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = sentiment.text,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(
                start = 4.dp,
                end = 4.dp,
                top = 2.dp,
                bottom = 2.dp
            ),
            maxLines = 1
        )
    }
}
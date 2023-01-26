package io.xavier.topwsb.presentation.common_composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.xavier.topwsb.domain.model.wsb_comment.Sentiment

/**
 * Custom composable for displaying sentiment within a card with a background that is specified
 * in [Sentiment].
 *
 * @param sentiment [Sentiment] to display in the card
 * @param modifier [Modifier]
 */
@Composable
fun SentimentCard(
    sentiment: Sentiment,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = sentiment.color,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = sentiment.text,
            fontWeight = FontWeight.SemiBold,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(
                start = 4.dp,
                end = 4.dp
            ),
            maxLines = 1
        )
    }
}
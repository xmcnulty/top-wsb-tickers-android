package io.xavier.topwsb.presentation.stock_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import io.xavier.topwsb.domain.model.Sentiment
import io.xavier.topwsb.presentation.common_composables.SentimentCard
import io.xavier.topwsb.presentation.theme.DarkSecondaryText
import io.xavier.topwsb.presentation.theme.defaultHorizontalPadding

@Composable
fun SectionInfoItemSentiment(
    sentiment: Sentiment,
    showDivider: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "WSB Sentiment",
            color = DarkSecondaryText,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.size(8.dp))

        SentimentCard(sentiment = sentiment)
    }

    if (showDivider) {
        Divider(
            modifier = Modifier
                .padding(horizontal = defaultHorizontalPadding)
                .alpha(.2f),
            color = DarkSecondaryText
        )
    }
}
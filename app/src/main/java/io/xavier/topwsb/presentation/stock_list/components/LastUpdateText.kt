package io.xavier.topwsb.presentation.stock_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Displays the time of the last update for the trending stocks list.
 * If the data is loading, 'Updating...' and CircularProgressIndicator will be shown.
 *
 * @param modifier [Modifier]
 * @param lastUpdateTime formatted time of last update
 * @param isRefreshing is the data refreshing/loading
 */
@Composable
fun LastUpdateText(
    modifier: Modifier = Modifier,
    lastUpdateTime: String,
    isRefreshing: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "Last update",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Medium
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = if (isRefreshing) "Updating" else lastUpdateTime,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.width(4.dp))

            if (isRefreshing) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    strokeWidth = 2.dp
                )
            }
        }
    }
}
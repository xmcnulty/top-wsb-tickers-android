package io.xavier.topwsb.presentation.stock_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.xavier.topwsb.R
import io.xavier.topwsb.domain.model.TrendingStock
import io.xavier.topwsb.presentation.common_composables.SentimentCard
import io.xavier.topwsb.presentation.theme.*

/**
 * Material 3 ListItem that displays a stock's ticker, WSB sentiment and number of comments
 * for the past 24-hours. ListItems are ranked by number of comments. Clicking a ListItem
 * opens a Screen that displays more information about the contained stock.
 *
 * @param trendingStock [TrendingStock] displayed in this ListItem
 * @param onItemClick Callback to navigate to new detailed screen for [trendingStock]
 */
@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListItem(
    trendingStock: TrendingStock,
    onItemClick: (TrendingStock) -> Unit
) {
    ListItem(
        leadingContent = {
            // Stock ticker contained in card
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(64.dp)
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = DarkBackgroundTranslucent,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = trendingStock.ticker,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        headlineText = {
            SentimentCard(sentiment = trendingStock.sentiment)
        },
        // number of comments
        supportingText = {
            Row(
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Text(
                    text = "Comments:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.size(4.dp))

                Card(
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(
                        text = trendingStock.commentCount.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 1.dp, bottom = 1.dp),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        },
        trailingContent = {

            Box(modifier = Modifier.size(16.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(DarkPrimaryText)
                )
            }
        },
        modifier = Modifier.clickable { onItemClick(trendingStock) }
    )

    Divider()
}
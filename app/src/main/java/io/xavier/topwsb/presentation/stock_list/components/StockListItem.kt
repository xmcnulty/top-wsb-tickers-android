package io.xavier.topwsb.presentation.stock_list.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.R
import io.xavier.topwsb.domain.model.trending_stock.TrendingStock
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
                    .size(32.dp)
            ) {
                trendingStock.logoUrl?.also { url ->
                    val fullUrl = "$url?apiKey=${BuildConfig.API_KEY_POLYGON}"

                    AsyncImage(
                        model = fullUrl,
                        contentDescription = "Stock Icon",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        onError = {
                            Log.e(
                                "Stock List Item",
                                """
                                    Error Loading ${trendingStock.ticker}
                                    URL: $fullUrl
                                    --------------------------------------
                                    ${it.result.throwable.localizedMessage}
                                """.trimIndent()
                            )
                        }
                    )
                } ?: run {
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
            }
        },
        headlineText = {
            Text(
                text = trendingStock.ticker,
                fontWeight = FontWeight.Bold,
                color = DarkPrimaryText,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        // number of comments
        supportingText = {
            Row(
                modifier = Modifier.padding(top = 2.dp)
            ) {
                SentimentCard(sentiment = trendingStock.sentiment)
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
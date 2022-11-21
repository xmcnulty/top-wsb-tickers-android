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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.xavier.topwsb.R
import io.xavier.topwsb.domain.model.Stock
import io.xavier.topwsb.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListItem(
    stock: Stock,
    onItemClick: (Stock) -> Unit
) {
    ListItem(
        leadingContent = {

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
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stock.ticker,
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

            val cardColor = if(stock.sentiment == "Bullish")
                PositiveTrend
            else
                NegativeTrend

            Card(
                shape = MaterialTheme.shapes.extraSmall,
                colors = CardDefaults.cardColors(
                    containerColor = cardColor,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text(
                    text = stock.sentiment,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = 4.dp,
                        end = 4.dp,
                        top = 2.dp,
                        bottom = 2.dp
                    ),
                    maxLines = 1
                )
            }
        },
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
                        text = stock.numberOfComments.toString(),
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
        modifier = Modifier.clickable { onItemClick(stock) }
    )

    Divider()
}

@Preview(showBackground = true)
@Composable
private fun StockListItemPreview() {
    TopWSBTheme {
        StockListItem(
            stock = Stock(
                numberOfComments = 25,
                sentiment = "Bullish",
                sentimentScore = 0.023,
                ticker = "AMD"
            ),
            onItemClick = {}
        )
    }
}
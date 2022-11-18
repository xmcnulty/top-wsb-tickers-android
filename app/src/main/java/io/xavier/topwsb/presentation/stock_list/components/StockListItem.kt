package io.xavier.topwsb.presentation.stock_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import io.xavier.topwsb.domain.model.Stock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListItem(
    stock: Stock,
    onItemClick: (Stock) -> Unit
) {
    ListItem(
        headlineText = {
            Text(
                text = stock.ticker,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
        },
        supportingText = {
            Row {
                // Todo: Put in a card
                Text(text = stock.numberOfComments.toString())
                Text(text = "Comments")
            }
        },
        modifier = Modifier.clickable { onItemClick(stock) }
    )
}
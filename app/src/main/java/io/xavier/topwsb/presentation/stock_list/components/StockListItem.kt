package io.xavier.topwsb.presentation.stock_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.xavier.topwsb.domain.model.Stock
import io.xavier.topwsb.R

@Composable
fun StockListItem(
    stock: Stock,
    onItemClick: (Stock) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(stock) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                text = stock.ticker
            )

            Text(
                text = "${stringResource(id = R.string.comments)} ${stock.numberOfComments}"
            )
        }
    }
}
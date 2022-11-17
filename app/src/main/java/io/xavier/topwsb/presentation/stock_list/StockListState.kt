package io.xavier.topwsb.presentation.stock_list

import io.xavier.topwsb.domain.model.Stock

data class StockListState(
    val isLoading: Boolean = false,
    val stocks: List<Stock> = emptyList(),
    val error: String = ""
)

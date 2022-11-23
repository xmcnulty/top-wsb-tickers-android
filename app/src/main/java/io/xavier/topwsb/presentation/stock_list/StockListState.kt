package io.xavier.topwsb.presentation.stock_list

import io.xavier.topwsb.domain.model.TrendingStock

data class StockListState(
    val isLoading: Boolean = false,
    val trendingStocks: List<TrendingStock> = emptyList(),
    val error: String = ""
)

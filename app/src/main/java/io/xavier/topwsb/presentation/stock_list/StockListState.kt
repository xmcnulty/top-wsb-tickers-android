package io.xavier.topwsb.presentation.stock_list

import io.xavier.topwsb.domain.model.trending_stock.TrendingStock

/**
 * State of the stock list screen.
 */
data class StockListState(
    val lastUpdateFormatted: String = "-",
    val isLoading: Boolean = true,
    val trendingStocks: List<TrendingStock> = emptyList(),
    val errorId: Int? = null
)

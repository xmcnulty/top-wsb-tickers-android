package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.presentation.stock_detail.chart.ChartState
import io.xavier.topwsb.presentation.stock_detail.market_data.MarketDataState

/**
 * State of the StockDetailScreen, divided into sub-states for each of the display sections.
 */
data class StockDetailState(
    val marketDataState: MarketDataState = MarketDataState.Error("Data not loaded"),
    val chartState: ChartState = ChartState.Error("Data not loaded"),
    val isChartVisible: Boolean = false
)

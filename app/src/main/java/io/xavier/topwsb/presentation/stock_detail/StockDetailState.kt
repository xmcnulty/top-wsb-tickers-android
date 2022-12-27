package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.presentation.stock_detail.components.chart.ChartState
import io.xavier.topwsb.presentation.stock_detail.components.comments.CommentsState
import io.xavier.topwsb.presentation.stock_detail.components.market_data.MarketDataState

/**
 * State of the StockDetailScreen, divided into sub-states for each of the display sections.
 *
 * @property marketDataState state of the screen section that displays market overview of a stock
 * @property chartState state of the chart data
 * @property commentsState state of the comments data
 * @property isChartVisible is the chart visible
 */
data class StockDetailState(
    val marketDataState: MarketDataState = MarketDataState.Error("Data not loaded"),
    val chartState: ChartState = ChartState.Error("Data not loaded"),
    val commentsState: CommentsState = CommentsState.Error("Data not loaded"),
    val isChartVisible: Boolean = false
)

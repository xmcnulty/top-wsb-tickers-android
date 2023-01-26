package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.presentation.stock_detail.components.chart.ChartState
import io.xavier.topwsb.presentation.stock_detail.components.comments.CommentsState

/**
 * State of the StockDetailScreen, divided into sub-states for each of the display sections.
 *
 * @property chartState state of the chart data
 * @property commentsState state of the comments data
 * @property isChartVisible is the chart visible
 */
data class StockDetailState(
    val chartState: ChartState = ChartState.Error(null),
    val commentsState: CommentsState = CommentsState.Error(null),
    val isChartVisible: Boolean = false
)

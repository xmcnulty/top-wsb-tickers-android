package io.xavier.topwsb.presentation.stock_detail

/**
 * State of the StockDetailScreen, divided into sub-states for each of the display sections.
 *
 * @property chartState state of the chart display area
 * @property commentState state of the comment display area
 *
 */
data class StockDetailState(
    var chartState: ChartState = ChartState(),
    var commentState: CommentState = CommentState(),
    var mktOverviewState: MktOverviewState = MktOverviewState()
)

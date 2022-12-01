package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.domain.model.StockOverview

/**
 * State of the StockDetailScreen, divided into sub-states for each of the display sections.
 *
 * @property chartState state of the chart display area
 * @property commentState state of the comment display area
 *
 */
data class StockDetailState(
    val stockOverview: StockOverview? = null,
    val stockOverviewLoading: Boolean = false,
    val stockOverviewError: String? = null
)

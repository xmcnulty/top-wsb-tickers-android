package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.domain.model.StockOverview
import io.xavier.topwsb.domain.model.chart_data.IntradayData

/**
 * State of the StockDetailScreen, divided into sub-states for each of the display sections.
 */
data class StockDetailState(
    // Stock overview
    val stockOverview: StockOverview? = null,
    val stockOverviewLoading: Boolean = false,
    val stockOverviewError: String? = null,
    val intradayData: IntradayData? = null,
    val isLoadingChart: Boolean = false,
    val chartError: String? = null
)

package io.xavier.topwsb.presentation.stock_detail.chart

import io.xavier.topwsb.domain.model.chart_data.IntradayData

/**
 * State of the chart displayed on the stock detail screen.
 */
sealed interface ChartState {
    object Loading : ChartState
    data class Error(val message: String) : ChartState
    data class Success(val data: IntradayData?) : ChartState
}
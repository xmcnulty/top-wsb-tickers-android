package io.xavier.topwsb.domain.repository.chart_data

import io.xavier.topwsb.domain.model.chart_data.ChartData

/**
 * Repository for fetching chart data. Data will either be fetched from Room database
 * or Polygon API.
 */
interface ChartDataRepository {
    /**
     * Gets chart data for a specific stock in a given time window.
     *
     * @param ticker ticker symbol of the stock/equity to get the data for
     * @param from start date for the chart data in YYYY-MM-DD format
     * @param to end date for the chart data in YYYY-MM-DD format
     * @return a list of chart data points
     */
    suspend fun getChartData(
        ticker: String,
        from: String,
        to: String
    ): ChartData
}
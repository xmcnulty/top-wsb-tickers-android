package io.xavier.topwsb.domain.repository.chart_data

interface ChartDataInterface {
    suspend fun getChartData(
        ticker: String
    )
}
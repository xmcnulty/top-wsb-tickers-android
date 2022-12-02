package io.xavier.topwsb.domain.model.chart_data

data class MarketChartData(
    val ticker: String,
    val timeZone: String,
    val interval: String,
    val dataPoints: List<MarketChartDataPoint>
) {
    init {
        dataPoints.sortedByDescending { dataPoint ->
            dataPoint.time
        }
    }
}

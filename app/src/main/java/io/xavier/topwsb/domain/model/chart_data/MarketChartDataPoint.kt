package io.xavier.topwsb.domain.model.chart_data

import java.time.LocalDateTime

/**
 * Data point on price chart.
 *
 * @property time time of the data (chart x-axis)
 * @property price closing price at time
 */
data class MarketChartDataPoint(
    val time: LocalDateTime,
    val price: Double
)

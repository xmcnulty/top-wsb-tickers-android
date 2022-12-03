package io.xavier.topwsb.domain.model.chart_data

/**
 * Data class to hold intra-day time series data. Contains a list
 * of intra-day datapoints along with some metadata.
 *
 * @property ticker stock ticker of this data
 * @property timeZone time zone the time series is in
 * @property interval time interval between each data point
 * @property dataPoints time-series data
 */
data class IntradayData(
    val ticker: String,
    val timeZone: String,
    val interval: String,
    val dataPoints: List<IntradayDataPoint>
) {
    /**
     * Print this intraday data. Mainly used for debugging.
     *
     * @return formatted string for this
     */
    override fun toString(): String {
        var str = "Intraday timeseries ($interval) for $ticker:\n"

        dataPoints.forEach { dataPoint ->
            str += "\t$dataPoint\n"
        }

        return str
    }
}

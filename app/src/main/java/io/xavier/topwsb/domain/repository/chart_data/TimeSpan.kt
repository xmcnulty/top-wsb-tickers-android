package io.xavier.topwsb.domain.repository.chart_data

/**
 * Timespan options for requests to Polygon aggregate endpoint.
 */
enum class TimeSpan(private val str: String) {
    MINUTE("minute"),
    HOUR("hour"),
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    QUARTER("quarter"),
    YEAR("year");

    override fun toString() = this.str
}
package io.xavier.topwsb.domain.model.chart_data

/**
 * Intervals for candlestick data.
 */
enum class IntradayInterval(val value: String) {
    ONE_MINUTE("1min"),
    FIVE_MINUTE("5min"),
    FIFTEEN_MINUTE("15min"),
    THIRTY_MINUTE("30min"),
    ONE_HOUR("60min");

    override fun toString() = this.value
}
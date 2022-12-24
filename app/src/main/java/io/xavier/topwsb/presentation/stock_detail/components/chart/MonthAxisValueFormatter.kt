package io.xavier.topwsb.presentation.stock_detail.components.chart

import com.github.mikephil.charting.formatter.ValueFormatter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Formats x-axis for candle stick chart with a month of data (x-axis).
 *
 * @param times ordered list of times for each candle corresponding to its index on the chart.
 */
class MonthAxisValueFormatter(
    val times: List<LocalDateTime>
) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        val index = value.toInt()
        val pattern = "d MMM"

        return times[index].format(DateTimeFormatter.ofPattern(pattern, Locale.getDefault()))
    }
}
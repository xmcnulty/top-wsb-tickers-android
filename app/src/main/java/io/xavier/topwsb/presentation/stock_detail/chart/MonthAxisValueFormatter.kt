package io.xavier.topwsb.presentation.stock_detail.chart

import com.github.mikephil.charting.formatter.ValueFormatter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MonthAxisValueFormatter(
    val times: List<LocalDateTime>
) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        val index = value.toInt()
        val pattern = "d MMM"

        return times[index].format(DateTimeFormatter.ofPattern(pattern, Locale.getDefault()))
    }
}
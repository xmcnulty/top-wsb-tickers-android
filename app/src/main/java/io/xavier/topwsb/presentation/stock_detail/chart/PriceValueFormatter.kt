package io.xavier.topwsb.presentation.stock_detail.chart

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.NumberFormat

class PriceValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.minimumFractionDigits = 0
        formatter.maximumFractionDigits = 2

        return formatter.format(value)
    }
}
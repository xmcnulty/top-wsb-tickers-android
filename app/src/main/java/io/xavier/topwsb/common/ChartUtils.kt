package io.xavier.topwsb.common

import androidx.compose.ui.graphics.toArgb
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import io.xavier.topwsb.presentation.stock_detail.components.chart.MonthAxisValueFormatter
import io.xavier.topwsb.presentation.stock_detail.components.chart.PriceValueFormatter
import io.xavier.topwsb.presentation.theme.DarkPrimaryText
import io.xavier.topwsb.presentation.theme.DarkSelectedCard
import java.time.LocalDateTime

/**
 * Utility function to prepare candle stick chart.
 *
 * @param times List of times (x-axis). Used for formatting/labeling
 */
fun BarLineChartBase<CandleData>.prepareChart(
    times: List<LocalDateTime>
) {
    this.setDrawGridBackground(false)
    this.description.isEnabled = false
    this.setDrawBorders(false)

    // if more than 100 entries are displayed in chart, no values will be drawn
    this.setMaxVisibleValueCount(20)

    this.setDrawGridBackground(false)

    // set x values
    this.xAxis.setDrawAxisLine(true)
    this.xAxis.axisLineColor = DarkSelectedCard.toArgb()
    this.xAxis.position = XAxis.XAxisPosition.BOTTOM
    this.xAxis.setDrawGridLines(false)
    this.xAxis.valueFormatter = MonthAxisValueFormatter(times)
    this.xAxis.textColor = DarkPrimaryText.toArgb()

    // set y values
    this.axisLeft.isEnabled = true
    this.axisRight.isEnabled = false
    this.axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
    this.axisLeft.setDrawGridLines(false)
    this.axisLeft.setDrawAxisLine(true)
    this.axisLeft.axisLineColor = DarkSelectedCard.toArgb()
    this.axisLeft.valueFormatter = PriceValueFormatter()
    //this.axisLeft.spaceTop = 15f
    this.axisLeft.textColor = DarkPrimaryText.toArgb()

    // enable touch gestures
    this.setTouchEnabled(false)

    // enable scaling and dragging
    this.isDragEnabled = false
    this.setScaleEnabled(false)

    // if disabled, scaling can be done on x- and y-axis separately
    this.setPinchZoom(false)

    // customize legend
    val l = this.legend
    l.isEnabled = false
    l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
    l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
    l.orientation = Legend.LegendOrientation.HORIZONTAL
    l.setDrawInside(false)
    l.form = Legend.LegendForm.SQUARE
    l.formSize = 9f
    l.textSize = 11f
    l.xEntrySpace = 4f
}
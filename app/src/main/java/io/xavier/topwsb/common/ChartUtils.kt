package io.xavier.topwsb.common

import androidx.compose.ui.graphics.toArgb
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter
import io.xavier.topwsb.presentation.theme.DarkPrimaryText
import io.xavier.topwsb.presentation.theme.DarkSelectedCard

/**
 * Utility function to prepare chart style and formatting.
 */
fun BarLineChartBase<*>.prepareChart() {
    this.setDrawGridBackground(true)
    this.description.isEnabled = true
    this.setDrawBorders(false)

    // if more than 100 entries are displayed in chart, no values will be drawn
    //this.setMaxVisibleValueCount(100)

    this.setDrawGridBackground(false)

    // set x values
    this.xAxis.setDrawAxisLine(true)
    this.xAxis.axisLineColor = DarkSelectedCard.toArgb()
    this.xAxis.position = XAxis.XAxisPosition.BOTTOM
    this.xAxis.setDrawGridLines(false)
    this.xAxis.valueFormatter = DefaultAxisValueFormatter(5)
    this.xAxis.textColor = DarkPrimaryText.toArgb()

    // set y values
    this.axisLeft.isEnabled = true
    this.axisRight.isEnabled = false
    this.axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
    this.axisLeft.setDrawGridLines(false)
    this.axisLeft.setDrawAxisLine(true)
    this.axisLeft.axisLineColor = DarkSelectedCard.toArgb()
    //this.axisLeft.spaceTop = 15f
    this.axisLeft.textColor = DarkPrimaryText.toArgb()

    // enable touch gestures
    this.setTouchEnabled(false)

    // enable scaling and dragging
    this.isDragEnabled = true
    this.setScaleEnabled(true)

    // if disabled, scaling can be done on x- and y-axis separately
    this.setPinchZoom(false)

    // customize legend
    val l = this.legend
    l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
    l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
    l.orientation = Legend.LegendOrientation.HORIZONTAL
    l.setDrawInside(false)
    l.form = Legend.LegendForm.SQUARE
    l.formSize = 9f
    l.textSize = 11f
    l.xEntrySpace = 4f
}
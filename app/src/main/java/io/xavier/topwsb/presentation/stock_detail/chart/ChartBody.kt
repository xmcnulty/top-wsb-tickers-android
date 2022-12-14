package io.xavier.topwsb.presentation.stock_detail.chart

import android.content.Context
import android.graphics.Paint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import io.xavier.topwsb.common.prepareChart
import io.xavier.topwsb.domain.model.chart_data.IntradayData
import io.xavier.topwsb.presentation.theme.DarkSelectedCard
import io.xavier.topwsb.presentation.theme.NegativeTrend
import io.xavier.topwsb.presentation.theme.PositiveTrend

@Composable
fun ChartBody(
    modifier: Modifier = Modifier,
    data: IntradayData
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            createCandleSticksChart(context, data)
        }
    )
}

/**
 * Sets up a candlestick chart with the data provided
 */
private fun createCandleSticksChart(
    context: Context,
    data: IntradayData
): BarLineChartBase<CandleData> {
    val chart = CandleStickChart(context)
    chart.prepareChart()

    chart.data = prepareCandleSticksData(data)

    return chart
}

/**
 * Maps [IntradayData] to [CandleData] for charting.
 */
private fun prepareCandleSticksData(
    data: IntradayData
): CandleData {
    val candleEntries: List<CandleEntry> = data.dataPoints.mapIndexed { index, dataPoint ->
        CandleEntry(
            index.toFloat(), // value on the x-axis. TODO: implement
            dataPoint.high.toFloat(),
            dataPoint.low.toFloat(),
            dataPoint.open.toFloat(),
            dataPoint.close.toFloat()
        )
    }

    val dataSet = CandleDataSet(candleEntries, data.ticker)

    //dataSet.color = DarkSecondaryText.toArgb()
    dataSet.setDrawIcons(false)
    dataSet.axisDependency = YAxis.AxisDependency.LEFT
    dataSet.shadowColor = DarkSelectedCard.toArgb()
    dataSet.decreasingColor = NegativeTrend.toArgb()
    dataSet.increasingColor = PositiveTrend.toArgb()
    dataSet.increasingPaintStyle = Paint.Style.FILL

    return CandleData(dataSet)
}
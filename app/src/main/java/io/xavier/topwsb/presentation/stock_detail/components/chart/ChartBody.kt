package io.xavier.topwsb.presentation.stock_detail.components.chart

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
import io.xavier.topwsb.domain.model.chart_data.ChartData
import io.xavier.topwsb.presentation.theme.DarkSecondaryText
import io.xavier.topwsb.presentation.theme.NegativeTrend
import io.xavier.topwsb.presentation.theme.PositiveTrend
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun ChartBody(
    modifier: Modifier = Modifier,
    data: ChartData
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
    data: ChartData
): BarLineChartBase<CandleData> {
    val chart = CandleStickChart(context)
    chart.prepareChart(
        times = data.dataPoints.map {
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(it.time),
                ZoneId.systemDefault()
            )
        }
    )

    chart.data = prepareCandleSticksData(data)

    return chart
}

/**
 * Maps [ChartData] to [CandleData] for charting.
 */
private fun prepareCandleSticksData(
    data: ChartData
): CandleData {
    val candleEntries: List<CandleEntry> = data.dataPoints.mapIndexed { index, dataPoint ->
        CandleEntry(
            index.toFloat(),
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
    dataSet.shadowColor = DarkSecondaryText.toArgb()
    dataSet.decreasingColor = NegativeTrend.toArgb()
    dataSet.increasingColor = PositiveTrend.toArgb()
    dataSet.increasingPaintStyle = Paint.Style.FILL

    return CandleData(dataSet)
}
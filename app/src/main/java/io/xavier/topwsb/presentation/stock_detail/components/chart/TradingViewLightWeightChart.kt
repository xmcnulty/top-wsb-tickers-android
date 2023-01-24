package io.xavier.topwsb.presentation.stock_detail.components.chart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.tradingview.lightweightcharts.api.chart.models.color.IntColor
import com.tradingview.lightweightcharts.api.chart.models.color.surface.SolidColor
import com.tradingview.lightweightcharts.api.options.models.GridLineOptions
import com.tradingview.lightweightcharts.api.options.models.GridOptions
import com.tradingview.lightweightcharts.api.options.models.layoutOptions
import com.tradingview.lightweightcharts.api.series.common.SeriesData
import com.tradingview.lightweightcharts.api.series.models.HistogramData
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.api.series.models.WhitespaceData
import com.tradingview.lightweightcharts.view.ChartsView
import io.xavier.topwsb.domain.model.chart_data.ChartData
import io.xavier.topwsb.presentation.theme.DarkBackground
import io.xavier.topwsb.presentation.theme.DarkPrimaryText

@Composable
fun TradingViewLightWeightChart(
    ticker: String,
    chartData: ChartData,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            val chartsView = ChartsView(context)

            chartsView.api.applyOptions {
                layout = layoutOptions {
                    background = SolidColor(DarkBackground.toArgb())
                    textColor = IntColor(DarkPrimaryText.toArgb())
                    grid = GridOptions(
                        vertLines = GridLineOptions(visible = false),
                        horzLines = GridLineOptions(visible = false)
                    )
                }
            }

            val areaData = chartData.dataPoints.map { dataPoint ->
                HistogramData(
                    Time.Utc(dataPoint.time),
                    dataPoint.close.toFloat()
                )
            }

            val data = listOf(
                HistogramData(Time.BusinessDay(2019, 6, 11), 40.01f),
                HistogramData(Time.BusinessDay(2019, 6, 12), 52.38f),
                HistogramData(Time.BusinessDay(2019, 6, 13), 36.30f),
                HistogramData(Time.BusinessDay(2019, 6, 14), 34.48f),
                WhitespaceData(Time.BusinessDay(2019, 6, 15)),
                WhitespaceData(Time.BusinessDay(2019, 6, 16)),
                HistogramData(Time.BusinessDay(2019, 6, 17), 41.50f),
                HistogramData(Time.BusinessDay(2019, 6, 18), 34.82f)
            )

            chartsView.api.addAreaSeries(
                onSeriesCreated = { series ->
                    val areaSeries = series

                    areaSeries.setData(areaData)
                }
            )

            chartsView
        }
    )
}
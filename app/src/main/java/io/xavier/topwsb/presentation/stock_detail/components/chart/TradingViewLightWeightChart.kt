package io.xavier.topwsb.presentation.stock_detail.components.chart

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.tradingview.lightweightcharts.api.chart.models.color.IntColor
import com.tradingview.lightweightcharts.api.chart.models.color.surface.SolidColor
import com.tradingview.lightweightcharts.api.options.enums.PriceAxisPosition
import com.tradingview.lightweightcharts.api.options.models.*
import com.tradingview.lightweightcharts.api.series.models.LineData
import com.tradingview.lightweightcharts.api.series.models.PriceFormat
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.runtime.plugins.PriceFormatter
import com.tradingview.lightweightcharts.view.ChartsView
import io.xavier.topwsb.domain.model.chart_data.ChartData
import io.xavier.topwsb.presentation.theme.DarkBackground
import io.xavier.topwsb.presentation.theme.DarkPrimaryText

@Composable
fun TradingViewLightWeightChart(
    chartData: ChartData,
    modifier: Modifier = Modifier
) {
    val chartFontFamily = MaterialTheme.typography.labelSmall.fontFeatureSettings

    AndroidView(
        modifier = modifier,
        factory = { context ->
            val chartsView = ChartsView(context)

            chartsView.api.timeScale.fitContent()

            chartsView.api.applyOptions {
                layout = layoutOptions {
                    background = SolidColor(DarkBackground.toArgb())
                    textColor = IntColor(DarkPrimaryText.toArgb())
                    fontFamily = chartFontFamily
                    grid = GridOptions(
                        vertLines = GridLineOptions(visible = false),
                        horzLines = GridLineOptions(visible = false)
                    )
                    kineticScroll = KineticScrollOptions(
                        touch = false,
                        mouse = false
                    )
                    handleScroll = HandleScrollOptions(
                        mouseWheel = false,
                        pressedMouseMove = false,
                        horzTouchDrag = false,
                        vertTouchDrag = false
                    )
                    rightPriceScale = PriceScaleOptions(
                        visible = true,
                        borderVisible = false,
                        position = PriceAxisPosition.LEFT
                    )
                    timeScale = TimeScaleOptions(
                        rightOffset = 1.0f,
                        fixRightEdge = true
                    )
                }
            }

            val areaData = chartData.dataPoints.map { dataPoint ->
                LineData(
                    time = Time.Utc(dataPoint.time / 1000),
                    dataPoint.close.toFloat()
                )
            }

            chartsView.api.addAreaSeries(
                options = AreaSeriesOptions(
                    crosshairMarkerVisible = false,
                    priceFormat = PriceFormat(
                        formatter = PriceFormatter("$"),
                        type = PriceFormat.Type.PRICE
                    )
                ),
                onSeriesCreated = { series ->
                    series.setData(areaData)
                }
            )

            chartsView
        }
    )
}
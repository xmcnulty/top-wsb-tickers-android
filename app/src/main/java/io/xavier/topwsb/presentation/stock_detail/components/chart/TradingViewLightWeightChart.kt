package io.xavier.topwsb.presentation.stock_detail.components.chart

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.tradingview.lightweightcharts.api.chart.models.color.IntColor
import com.tradingview.lightweightcharts.api.chart.models.color.surface.SolidColor
import com.tradingview.lightweightcharts.api.options.models.*
import com.tradingview.lightweightcharts.api.series.models.LineData
import com.tradingview.lightweightcharts.api.series.models.PriceFormat
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.runtime.plugins.DateTimeFormat
import com.tradingview.lightweightcharts.runtime.plugins.PriceFormatter
import com.tradingview.lightweightcharts.runtime.plugins.TimeFormatter
import com.tradingview.lightweightcharts.view.ChartsView
import io.xavier.topwsb.domain.model.chart_data.ChartData
import io.xavier.topwsb.presentation.theme.ChartColors
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
                        touch = true,
                        mouse = false
                    )
                    handleScroll = HandleScrollOptions(
                        mouseWheel = false,
                        pressedMouseMove = false,
                        horzTouchDrag = true,
                        vertTouchDrag = true
                    )
                    rightPriceScale = PriceScaleOptions(
                        borderVisible = false,
                        entireTextOnly = true,
                        drawTicks = false
                    )
                    timeScale = TimeScaleOptions(
                        fixRightEdge = true,
                        fixLeftEdge = true,
                        borderVisible = false
                    )
                    localization = LocalizationOptions(
                        priceFormatter = PriceFormatter("\${price:#0:#0}"),
                        dateFormat = "MMM dd \n hh:mm",
                        timeFormatter = TimeFormatter(
                            locale = "us-US",
                            dateTimeFormat = DateTimeFormat.DATE_TIME
                        )
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
                options = areaSeriesOptions {
                    crosshairMarkerVisible = false

                    priceFormat = PriceFormat(
                        formatter = PriceFormatter("\${price:#0:#0}"),
                        type = PriceFormat.Type.PRICE
                    )

                    when {
                        areaData.first().value > areaData.last().value -> {
                            topColor = ChartColors.negativeTrendTop
                            bottomColor = ChartColors.negativeTrendBottom
                            lineColor = ChartColors.negativeTrendTop
                        }
                        else -> {
                            topColor = ChartColors.positiveTrendTop
                            bottomColor = ChartColors.colPositiveTrendBottom
                            lineColor = ChartColors.positiveTrendTop
                        }
                    }
                },
                onSeriesCreated = { series ->
                    series.setData(areaData)
                }
            )

            chartsView
        }
    )
}
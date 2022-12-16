package io.xavier.topwsb.domain.model.chart_data

import androidx.compose.ui.graphics.Color

data class ChartData(
    val intraData: IntradayData,
    val startPrice: String,
    val startPriceDate: String,
    val lowPrice: String,
    val lowPriceDate: String,
    val highPrice: String,
    val highPriceDate: String,
    val changePercentage: String,
    val trendColor: Color
)

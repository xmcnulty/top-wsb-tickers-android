package io.xavier.topwsb.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.tradingview.lightweightcharts.api.chart.models.color.IntColor

/*val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)*/

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val PositiveTrend = Color(0xFF65C466)
val NegativeTrend = Color(0xFFEB4E3D)

// Dark theme
val DarkBackground = Color(0xFF191919)
val DarkPrimaryText = Color(0xFFEBEBEB)
val DarkSecondaryText = Color(0xFFB4B4B4)
//val DarkTopAppBarCollapsed = Color(0xFF212121)
val DarkDarkSelectedChip = Color(0xFF3B3B3D)
val DarkSelectedCard = Color(0xFF565656)
val DarkBackgroundTranslucent = Color(0xFF262626)

// Color values for TradingView lightweight charts
object ChartColors {
    val positiveTrendTop: IntColor = IntColor(
        value = PositiveTrend.toArgb()
    )
    val colPositiveTrendBottom: IntColor = IntColor(
        value = Color(40, 221, 100, 0).toArgb()
    )

    val negativeTrendTop: IntColor = IntColor(
        value = NegativeTrend.toArgb()
    )
    val negativeTrendBottom: IntColor = IntColor(
        value = Color(230, 79, 25, 0).toArgb()
    )
}
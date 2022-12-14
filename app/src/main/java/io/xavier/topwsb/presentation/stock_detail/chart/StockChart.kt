package io.xavier.topwsb.presentation.custom_composables

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.xavier.topwsb.domain.model.DataPoint
import io.xavier.topwsb.domain.model.chart_data.IntradayDataPoint
import io.xavier.topwsb.presentation.theme.DarkPrimaryText
import kotlin.math.roundToInt

/**
 * Composes a LineChart to display stock price.
 * Inspired by
 * https://github.com/philipplackner/StockMarketApp/blob/final/app/src/main/java/com/plcoding/stockmarketapp/presentation/company_info/StockChart.kt
 */
@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<IntradayDataPoint>,
    graphColor: Color,
    showDashedLine: Boolean,
    showYLabels: Boolean = false
) {
    val spacing = 100f
    val transparentGraphColor = remember(key1 = graphColor) {
        graphColor.copy(alpha = 0.5f)
    }

    // calculate upper and lower bounds of the chart data
    val upperValue = remember(data) {
        (data.maxOfOrNull {
            it.close
        }?.plus(1))?.roundToInt() ?: 0
    }

    val lowerValue = remember(data) {
        data.minOfOrNull {
            it.close
        }?.toInt() ?: 0
    }

    val density = LocalDensity.current
    val textPaint = remember {
        Paint().apply {
            color = DarkPrimaryText.toArgb()
            textAlign = Paint.Align.CENTER
            textSize = density.run {
                12.sp.toPx()
            }
        }
    }

    Canvas(
        modifier = modifier
    ) {

        // draw x-axis labels
        val spacePerHour = (size.width - spacing) / data.size

        (data.indices step 2).forEach { index ->
            val info = data[index]
            val time = info.time.dayOfMonth

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    time.toString(),
                    spacing + index * spacePerHour,
                    size.height - 5,
                    textPaint
                )
            }
        }
    }
}
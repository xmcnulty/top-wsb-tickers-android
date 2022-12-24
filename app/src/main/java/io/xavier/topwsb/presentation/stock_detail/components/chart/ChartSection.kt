package io.xavier.topwsb.presentation.stock_detail.components.chart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.xavier.topwsb.presentation.theme.defaultHorizontalPadding

/**
 * Composable section of screen that contains candle stick chart.
 * @param chartState state of chart data
 */
@Composable
fun ChartSection(
    chartState: ChartState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultHorizontalPadding),
        contentAlignment = Alignment.Center
    ) {
        when (chartState) {
            is ChartState.Success -> {
                chartState.data?.let {
                    ChartBody(
                        modifier = Modifier
                            .fillMaxSize(),
                        data = it
                    )
                }
            }
            is ChartState.Error -> {
                // TODO: Format
                Text(text = chartState.message)
            }
            ChartState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}
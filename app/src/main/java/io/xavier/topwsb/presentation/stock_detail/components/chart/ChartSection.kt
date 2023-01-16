package io.xavier.topwsb.presentation.stock_detail.components.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.xavier.topwsb.R
import io.xavier.topwsb.presentation.theme.DarkBackgroundTranslucent
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
                Icon(
                    painterResource(id = R.drawable.outline_error),
                    contentDescription = "Error",
                    modifier = Modifier.requiredSize(48.dp),
                    tint = DarkBackgroundTranslucent
                )
            }
            ChartState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}
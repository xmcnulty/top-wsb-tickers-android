package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.data.remote.dto.intraday_data.IntradayDataDto
import io.xavier.topwsb.domain.model.chart_data.ChartData

fun IntradayDataDto.toChartData(): ChartData = ChartData(
    startPrice =
)
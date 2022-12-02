package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.data.remote.dto.intraday_data.ChartDataDto
import io.xavier.topwsb.data.remote.dto.intraday_data.ChartDataPointDto
import io.xavier.topwsb.domain.model.chart_data.MarketChartData
import io.xavier.topwsb.domain.model.chart_data.MarketChartDataPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Maps a chart data point data transfer object (DTO) to a [MarketChartDataPoint].
 *
 * @param dateTime string formatted date and time for this data point
 * @return a [MarketChartDataPoint] object with [dateTime] converted to a [LocalDateTime] object
 */
fun ChartDataPointDto.toChartDataPoint(
    dateTime: String
): MarketChartDataPoint {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val date = LocalDateTime.parse(dateTime, formatter)

    return MarketChartDataPoint(
        time = date,
        price = close.toDouble()
    )
}

fun ChartDataDto.toChartData(): MarketChartData {
    val dataPoints = timeSeries.map { entry ->
        entry.value.toChartDataPoint(entry.key)
    }

    return MarketChartData(
        ticker = metaData.symbol,
        timeZone = metaData.timeZone,
        interval = metaData.interval,
        dataPoints = dataPoints
    )
}
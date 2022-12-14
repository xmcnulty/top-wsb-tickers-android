package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.data.remote.dto.intraday_data.IntradayDataDto
import io.xavier.topwsb.data.remote.dto.intraday_data.IntradayDataPointDto
import io.xavier.topwsb.domain.model.chart_data.IntradayData
import io.xavier.topwsb.domain.model.chart_data.IntradayDataPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Maps a chart data point data transfer object (DTO) to a [IntradayDataPoint].
 *
 * @param dateTime string formatted date and time for this data point
 * @return a [IntradayDataPoint] object with [dateTime] converted to a [LocalDateTime] object
 */
fun IntradayDataPointDto.toChartDataPoint(
    dateTime: String
): IntradayDataPoint {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val date = LocalDateTime.parse(dateTime, formatter)

    return IntradayDataPoint(
        time = date,
        open = open.toDouble(),
        high = high.toDouble(),
        low = low.toDouble(),
        close = close.toDouble()
    )
}

/**
 * Maps data transfer object (DTO) from api to [IntradayData]
 *
 * @return [IntradayData]
 */
fun IntradayDataDto.toIntradayData(): IntradayData {
    val dataPoints = timeSeries.map { entry ->
        entry.value.toChartDataPoint(entry.key)
    }.sortedBy {
        it.time
    }

    return IntradayData(
        ticker = metaData.symbol,
        timeZone = metaData.timeZone,
        interval = metaData.interval,
        dataPoints = dataPoints
    )
}
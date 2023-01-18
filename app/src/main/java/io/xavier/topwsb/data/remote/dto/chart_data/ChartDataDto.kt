package io.xavier.topwsb.data.remote.dto.chart_data

data class ChartDataDto(
    val adjusted: Boolean,
    val queryCount: Int,
    val request_id: String,
    val results: List<Result>,
    val resultsCount: Int,
    val status: String,
    val ticker: String
)
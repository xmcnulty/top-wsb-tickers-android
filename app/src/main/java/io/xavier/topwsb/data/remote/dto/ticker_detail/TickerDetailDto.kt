package io.xavier.topwsb.data.remote.dto.ticker_detail

data class TickerDetailDto(
    val request_id: String,
    val results: Results?,
    val status: String,
    val message: String?
)
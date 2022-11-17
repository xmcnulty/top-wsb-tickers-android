package io.xavier.topwsb.data.remote.dto

import io.xavier.topwsb.domain.model.StonkDetail

data class StonkDetailDto(
    val symbol: String,
    val open: String,
    val high: String,
    val low: String,
    val price: String,
    val volume: String,
    val latest_trading_day: String,
    val previous_close: String,
    val change: String,
    val change_percent: String
)

fun StonkDetailDto.toStonkDetail(): StonkDetail {
    return StonkDetail(
        symbol = symbol,
        price = price.toDouble(),
        volume = volume.toDouble(),
        lastTradingDay = latest_trading_day,
        previousClose = previous_close.toDouble(),
        changePercent = change_percent.filter {
            it.isDigit() || it == '.'
        }.toDouble()
    )
}
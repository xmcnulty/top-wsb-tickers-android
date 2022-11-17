package io.xavier.topwsb.domain.model

data class StockDetail(
    val symbol: String,
    val price: Double,
    val volume: Double,
    val lastTradingDay: String,
    val previousClose: Double,
    val changePercent: Double
)

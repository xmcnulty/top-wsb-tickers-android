package io.xavier.topwsb.domain.model

data class StonkDetail(
    val symbol: String,
    val price: Double,
    val volume: Double,
    val lastTradingDay: String,
    val previousClose: Double,
    val changePercent: Double
)

package io.xavier.topwsb.data.remote.dto

data class Meta(
    val chartPreviousClose: Double,
    val currency: String,
    val dataGranularity: String,
    val exchangeName: String,
    val exchangeTimezoneName: String,
    val firstTradeDate: Int,
    val gmtoffset: Int,
    val instrumentType: String,
    val previousClose: Double,
    val priceHint: Int,
    val range: String,
    val regularMarketPrice: Double,
    val regularMarketTime: Int,
    val scale: Int,
    val symbol: String,
    val timezone: String
)
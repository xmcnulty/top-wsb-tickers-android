package io.xavier.topwsb.data.remote.dto.trending_stocks

data class TrendingStockDto(
    val no_of_comments: Int,
    val sentiment: String,
    val sentiment_score: Double,
    val ticker: String
)
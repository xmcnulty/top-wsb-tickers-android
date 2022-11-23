package io.xavier.topwsb.data.remote.dto.trending_stocks

import io.xavier.topwsb.domain.model.TrendingStock

data class TrendingStockDto(
    val no_of_comments: Int,
    val sentiment: String,
    val sentiment_score: Double,
    val ticker: String
)

fun TrendingStockDto.toStock(): TrendingStock {
    return TrendingStock(
        numberOfComments = no_of_comments,
        sentiment = sentiment,
        sentimentScore = sentiment_score,
        ticker = ticker
    )
}
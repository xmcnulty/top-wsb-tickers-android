package io.xavier.topwsb.data.remote.dto

import io.xavier.topwsb.domain.model.TrendingStock

data class StockDto(
    val no_of_comments: Int,
    val sentiment: String,
    val sentiment_score: Double,
    val ticker: String
)

fun StockDto.toStock(): TrendingStock {
    return TrendingStock(
        numberOfComments = no_of_comments,
        sentiment = sentiment,
        sentimentScore = sentiment_score,
        ticker = ticker
    )
}
package io.xavier.topwsb.data.remote.dto

import io.xavier.topwsb.domain.model.Stock

data class StockDto(
    val no_of_comments: Int,
    val sentiment: String,
    val sentiment_score: Double,
    val ticker: String
)

fun StockDto.toStock(): Stock {
    return Stock(
        numberOfComments = no_of_comments,
        sentiment = sentiment,
        sentimentScore = sentiment_score,
        ticker = ticker
    )
}
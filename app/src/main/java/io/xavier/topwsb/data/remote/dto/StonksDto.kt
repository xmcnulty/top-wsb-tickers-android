package io.xavier.topwsb.data.remote.dto

import io.xavier.topwsb.domain.model.Stonk

data class StonksDto(
    val no_of_comments: Int,
    val sentiment: String,
    val sentiment_score: Double,
    val ticker: String
)

fun StonksDto.toStonk(): Stonk {
    return Stonk(
        no_of_comments = no_of_comments,
        sentiment = sentiment,
        sentiment_score = sentiment_score,
        ticker = ticker
    )
}
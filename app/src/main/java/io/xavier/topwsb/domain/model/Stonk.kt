package io.xavier.topwsb.domain.model

data class Stonk(
    val no_of_comments: Int,
    val sentiment: String,
    val sentiment_score: Double,
    val ticker: String
)

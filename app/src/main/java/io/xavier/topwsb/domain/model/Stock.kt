package io.xavier.topwsb.domain.model

data class Stock(
    val numberOfComments: Int,
    val sentiment: String,
    val sentimentScore: Double,
    val ticker: String
)

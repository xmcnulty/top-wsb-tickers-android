package io.xavier.topwsb.domain.model

/**
 * Data class for information of trending returned form tradestie API
 */
data class TrendingStock(
    val numberOfComments: Int,
    val sentiment: String,
    val sentimentScore: Double,
    val ticker: String
)

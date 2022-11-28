package io.xavier.topwsb.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entry for a trending stock ticker. Details returned from the Tradestie api.
 *
 * @property ticker ticker of this stock
 * @property lastUpdatedUtc time from the epoch that this entity was last updated
 * @property numberOfComments number of comments on /r/wallstreetbets mentioning [ticker]
 * @property sentiment daily wallstreetbets sentiment for [ticker]
 * @property sentimentScore daily sentiment score for [ticker]
 */
@Entity
data class TrendingStockEntity(
    @PrimaryKey
    val ticker: String,
    val lastUpdatedUtc: Int,
    val numberOfComments: Int,
    val sentiment: String,
    val sentimentScore: Double
)

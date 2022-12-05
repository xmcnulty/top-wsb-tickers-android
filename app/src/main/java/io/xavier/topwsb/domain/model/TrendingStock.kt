package io.xavier.topwsb.domain.model

import io.xavier.topwsb.data.local.entities.TrendingStockEntity

/**
 * Data class for a trending stock, as returned from remote API. Object is also
 * stored in local storage, and each field has a corresponding column name.
 *
 * @property ticker exchange ticker of the stock
 * @property lastUpdatedUtc time in milliseconds this object was retrieved
 * @property numberOfComments number of comments mentioning this stock on wallstreetbets
 * @property sentiment overall wallstreetbets sentiment for this stock
 * @property sentimentScore sentiment score for this stock on wallstreetbets
 */
data class TrendingStock(
    val ticker: String,
    val lastUpdatedUtc: Long,
    val numberOfComments: Int,
    val sentiment: Sentiment,
    val sentimentScore: Double
) {

    companion object {
        /**
         * Creates a [TrendingStock] object from a [TrendingStockEntity].
         *
         * @param entity [TrendingStockEntity]
         * @return new [TrendingStock] created from [entity]
         */
        fun fromEntity(entity: TrendingStockEntity): TrendingStock = TrendingStock(
            ticker = entity.ticker,
            lastUpdatedUtc = entity.lastUpdatedUtc,
            numberOfComments = entity.numberOfComments,
            sentiment = Sentiment.fromName(entity.sentiment),
            sentimentScore = entity.sentimentScore
        )
    }
}

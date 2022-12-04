package io.xavier.topwsb.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.xavier.topwsb.common.*
import io.xavier.topwsb.data.remote.dto.trending_stocks.TrendingStockDto

/**
 * Entity object used for storing a trending stock data object in the local cache.
 *
 * @property ticker exchange ticker of the stock
 * @property lastUpdatedUtc time in milliseconds this object was retrieved
 * @property numberOfComments number of comments mentioning this stock on wallstreetbets
 * @property sentiment overall wallstreetbets sentiment for this stock
 * @property sentimentScore sentiment score for this stock on wallstreetbets
 */
@Entity(tableName = TABLE_NAME_TRENDING_STOCKS)
data class TrendingStockEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = TICKER_STOCK_COL_NAME)
    val ticker: String,

    @ColumnInfo(name = LAST_UPDATED_COL_NAME)
    val lastUpdatedUtc: Long,

    @ColumnInfo(name = NUMBER_OF_COMMENTS_COL_NAME)
    val numberOfComments: Int,

    @ColumnInfo(name = SENTIMENT_COL_NAME)
    val sentiment: String,

    @ColumnInfo(name = SENTIMENT_SCORE_COL_NAME)
    val sentimentScore: Double
) {

    companion object {

        /**
         * Creates an entity for room storage from a data transfer object (DTO)
         *
         * @param dto [TrendingStockDto] to create from
         * @param updateTime time in milliseconds [dto] was fetched, and hence the list of trending
         *  stocks were last updated
         * @return [TrendingStockEntity] created from [dto] and [updateTime]
         */
        fun fromDto(
            dto: TrendingStockDto,
            updateTime: Long
        ): TrendingStockEntity = TrendingStockEntity(
            ticker = dto.ticker,
            lastUpdatedUtc = updateTime,
            numberOfComments = dto.no_of_comments,
            sentiment = dto.sentiment,
            sentimentScore = dto.sentiment_score
        )
    }
}

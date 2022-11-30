package io.xavier.topwsb.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.xavier.topwsb.common.*

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
@Entity(tableName = TABLE_NAME_TRENDING_STOCKS)
data class TrendingStock(
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
)

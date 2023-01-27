package io.xavier.topwsb.domain.model.trending_stock

import android.os.Parcelable
import io.xavier.topwsb.data.local.entities.TrendingStockEntity
import io.xavier.topwsb.domain.model.wsb_comment.Sentiment
import kotlinx.parcelize.Parcelize

/**
 * Data class for a trending stock, as returned from remote API. Object is also
 * stored in local storage, and each field has a corresponding column name.
 *
 * @property ticker exchange ticker of the stock
 * @property lastUpdatedUtc time in milliseconds this object was retrieved
 * @property commentCount number of comments mentioning this stock on wallstreetbets
 * @property sentiment overall wallstreetbets sentiment for this stock
 * @property sentimentScore sentiment score for this stock on wallstreetbets
 */
@Parcelize
data class TrendingStock(
    val ticker: String,
    val name: String,
    val type: StockType,
    val marketCap: Double?,
    val sharesOutstanding: Long,
    val logoUrl: String?,
    val lastUpdatedUtc: Long,
    val sentiment: Sentiment,
    val sentimentScore: Double,
    val commentCount: Int
) : Parcelable {
    fun toEntity(): TrendingStockEntity = TrendingStockEntity(
        ticker = this.ticker,
        name = this.name,
        type = this.type.code,
        marketCap = this.marketCap,
        sharesOutstanding = this.sharesOutstanding,
        logoUrl = this.logoUrl,
        lastUpdatedUtc = this.lastUpdatedUtc,
        sentiment = this.sentiment.text,
        sentimentScore = this.sentimentScore,
        numberOfComments = this.commentCount
    )

    companion object {
        fun build(entity: TrendingStockEntity): TrendingStock = TrendingStock(
            ticker = entity.ticker,
            name = entity.name,
            type = StockType.build(entity.type),
            marketCap = entity.marketCap,
            sharesOutstanding = entity.sharesOutstanding,
            logoUrl = entity.logoUrl,
            lastUpdatedUtc = entity.lastUpdatedUtc,
            sentiment = Sentiment.fromName(entity.sentiment),
            sentimentScore = entity.sentimentScore,
            commentCount = entity.numberOfComments
        )
    }
}

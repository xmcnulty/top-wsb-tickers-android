package io.xavier.topwsb.domain.model.wsb_comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import io.xavier.topwsb.common.TABLE_NAME_COMMENTS
import io.xavier.topwsb.common.TICKER_STOCK_COL_NAME
import io.xavier.topwsb.data.local.entities.TrendingStockEntity
import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentDto

/**
 * Data class for a comment on the /r/wallstreetbets subreddit.
 *
 * @property text comment text
 * @property createdUtc seconds from epoch the comment was posted to reddit
 * @property author comment author
 * @property link URL to the reddit comment
 * @property ticker ticker of stock this comment references
 */
@Entity(
    tableName = TABLE_NAME_COMMENTS,
    foreignKeys = [ForeignKey(
        entity = TrendingStockEntity::class,
        parentColumns = arrayOf(TICKER_STOCK_COL_NAME),
        childColumns = arrayOf(TICKER_STOCK_COL_NAME),
        onDelete = CASCADE
    )]
)
data class WsbComment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "comment_id")
    val id: Int,
    @ColumnInfo(name = TICKER_STOCK_COL_NAME)
    val ticker: String,
    val text: String,
    val createdUtc: Long,
    val author: String,
    val link: String
) {
    companion object {
        /**
         * Builds a WsbComment object from a data transfer object.
         *
         * @param dto [WsbCommentDto] retrieved from remote api call
         * @param ticker symbol of the stock this comment is in reference to
         * @return [WsbComment] referencing [ticker] built from [dto]
         */
        fun buildFromDto(dto: WsbCommentDto, ticker: String): WsbComment = WsbComment(
            id = 0,
            text = dto.body,
            ticker = ticker,
            createdUtc = dto.created_utc * 1_000, // convert from seconds to ms
            author = dto.author,
            link = dto.permalink
        )
    }
}
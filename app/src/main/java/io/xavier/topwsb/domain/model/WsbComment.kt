package io.xavier.topwsb.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import io.xavier.topwsb.common.TABLE_NAME_COMMENTS
import io.xavier.topwsb.common.TICKER_STOCK_COL_NAME
import io.xavier.topwsb.data.local.entities.TrendingStockEntity

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
)
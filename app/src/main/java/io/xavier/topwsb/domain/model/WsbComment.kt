package io.xavier.topwsb.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import io.xavier.topwsb.common.TICKER_COMMENT
import io.xavier.topwsb.common.TICKER_STOCK_COL_NAME
import io.xavier.topwsb.data.local.entities.TrendingStockEntity

/**
 * Data class for a comment on the /r/wallstreetbets subreddit.
 *
 * @property text comment text
 * @property createdUtc seconds from epoch the comment was posted to reddit
 * @property author comment author
 * @property permalink URL to the reddit comment
 * @property ticker ticker of stock this comment references
 */
@Entity(
    tableName = "WSB_COMMENTS",
    foreignKeys = [ForeignKey(
        entity = TrendingStockEntity::class,
        parentColumns = arrayOf(TICKER_STOCK_COL_NAME),
        childColumns = arrayOf(TICKER_COMMENT),
        onUpdate = CASCADE,
        onDelete = CASCADE
    )]
)
data class WsbComment(
    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0,
    @ColumnInfo(name = TICKER_COMMENT)
    val ticker: String,
    val text: String,
    val createdUtc: Long,
    val author: String,
    val permalink: String
)
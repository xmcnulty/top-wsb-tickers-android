package io.xavier.topwsb.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * How to store a wallstreetbets data object into the local cache.
 *
 * @property commentTicker stock ticker of the element being mentioned in the comment
 * @property lastUpdatedUtc time this entity was last updated from the epoch
 * @property text the text of the comment being saved
 * @property createdUtc the time this comment was created from the epoch
 * @property author the reddit username for the author of this comment
 * @property permalink reddit link to this comment
 * @property id unique identifier for the entity column.
 *  Hash code of [commentTicker] and [createdUtc]
 */
@Entity(
    foreignKeys = [ForeignKey(
        entity = TrendingStockEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("commentTicker"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class WsbCommentEntity(
    val commentTicker: String,
    val lastUpdatedUtc: Int,
    val text: String,
    val createdUtc: Int,
    val author: String,
    val permalink: String
) {
    @PrimaryKey
    val id = (commentTicker + createdUtc).hashCode()
}
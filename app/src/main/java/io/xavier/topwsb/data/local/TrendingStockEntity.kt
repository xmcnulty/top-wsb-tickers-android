package io.xavier.topwsb.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrendingStockEntity(
    val numberOfComments: Int,
    val sentiment: String,
    val sentimentScore: Double,
    val ticker: String,
    @PrimaryKey val id: Int? = null
)

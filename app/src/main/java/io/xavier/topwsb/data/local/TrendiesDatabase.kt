package io.xavier.topwsb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.xavier.topwsb.data.local.entities.TrendingStockEntity
import io.xavier.topwsb.domain.model.MarketData
import io.xavier.topwsb.domain.model.WsbComment

/**
 * Database used to cache trending stock information.
 */
@Database(
    entities = [TrendingStockEntity::class, MarketData::class, WsbComment::class],
    version = 1,
    exportSchema = false
)
abstract class TrendiesDatabase: RoomDatabase() {
    abstract val trendingStockDao: TrendingStockDao
    abstract val commentsDao: WsbCommentsDao
    abstract val stockOverviewDao: StockOverviewDao
}
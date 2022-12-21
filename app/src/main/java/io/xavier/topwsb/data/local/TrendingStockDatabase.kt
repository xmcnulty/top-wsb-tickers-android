package io.xavier.topwsb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.xavier.topwsb.data.local.entities.TrendingStockEntity
import io.xavier.topwsb.domain.model.MarketData

/**
 * Database used to cache trending stock information.
 */
@Database(
    entities = [TrendingStockEntity::class, MarketData::class],
    version = 1,
    exportSchema = false
)
abstract class TrendingStockDatabase: RoomDatabase() {
    abstract val dao: TrendingStockDao
}
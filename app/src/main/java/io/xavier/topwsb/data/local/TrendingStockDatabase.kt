package io.xavier.topwsb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Database used to cache trending stock information.
 */
@Database(
    entities = [TrendingStockEntity::class],
    version = 1
)
abstract class TrendingStockDatabase: RoomDatabase() {
    abstract val dao: TrendingStockDao
}
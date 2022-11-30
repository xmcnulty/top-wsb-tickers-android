package io.xavier.topwsb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.xavier.topwsb.domain.model.StockOverview
import io.xavier.topwsb.domain.model.TrendingStock

/**
 * Database used to cache trending stock information.
 */
@Database(
    entities = [TrendingStock::class, StockOverview::class],
    version = 1,
    exportSchema = false
)
abstract class TrendingStockDatabase: RoomDatabase() {
    abstract val dao: TrendingStockDao
}
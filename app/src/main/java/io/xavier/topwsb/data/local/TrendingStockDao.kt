package io.xavier.topwsb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Interface for accessing and modifying trending stocks in a local database.
 */
@Dao
interface TrendingStockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingStocks(
        trendingStockEntities: List<TrendingStockEntity>
    )

    @Query("DELETE FROM trendingstockentity")
    suspend fun clearTrendingStocks()

    @Query(
        """
            SELECT *
            FROM trendingstockentity
            WHERE UPPER(:query) == ticker
        """
    )
    suspend fun searchTrendingStockListing(query: String): List<TrendingStockEntity>
}
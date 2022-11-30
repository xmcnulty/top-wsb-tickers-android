package io.xavier.topwsb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.xavier.topwsb.common.NUMBER_OF_COMMENTS_COL_NAME
import io.xavier.topwsb.common.TABLE_NAME_TRENDING_STOCKS
import io.xavier.topwsb.domain.model.TrendingStock

/**
 * Interface for accessing and modifying trending stocks in a local database.
 */
@Dao
interface TrendingStockDao {

    /**
     * Insert a new list of trending stocks.
     *
     * @param trendingStockEntities stocks entities to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingStocks(
        trendingStockEntities: List<TrendingStock>
    )

    /**
     * Delete all trending stock entities from the database
     */
    @Query("DELETE FROM $TABLE_NAME_TRENDING_STOCKS")
    suspend fun clearTrendingStocks()

    /**
     * Gets all trending stocks from the database.
     *
     * @return list of all [TrendingStock]s in the database in descending order by number
     * of comments.
     */
    @Query("""
        SELECT *
        FROM $TABLE_NAME_TRENDING_STOCKS
        ORDER BY $NUMBER_OF_COMMENTS_COL_NAME DESC
    """)
    suspend fun getTrendingStocks(): List<TrendingStock>

    /**
     * Query and return a trending stock from the database
     *
     * @param ticker ticker of the stock to query
     * @return [TrendingStock] with the provided ticker
     */
    @Query(
        """
            SELECT *
            FROM $TABLE_NAME_TRENDING_STOCKS
            WHERE UPPER(:ticker) == ticker
        """
    )
    suspend fun getTrendingStock(ticker: String): TrendingStock
}
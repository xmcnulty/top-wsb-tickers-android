package io.xavier.topwsb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.xavier.topwsb.common.TABLE_NAME_STOCK_OVERVIEW
import io.xavier.topwsb.common.TICKER_OVERVIEW_COL_NAME
import io.xavier.topwsb.domain.model.MarketData

@Dao
interface StockOverviewDao {
    /**
     * Fetches a [MarketData] object with given ticker.
     *
     * @param ticker ticker to query
     * @return list of [MarketData]. If matching overview is found, list should contain only
     * on element as [ticker] is a unique key. If not found, the list will be empty.
     */
    @Query(
        """
            SELECT *
            FROM $TABLE_NAME_STOCK_OVERVIEW
            WHERE UPPER(:ticker) == $TICKER_OVERVIEW_COL_NAME
        """
    )
    suspend fun getStockOverview(ticker: String): List<MarketData>

    /**
     * Insert [MarketData] into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockOverview(overview: MarketData)
}
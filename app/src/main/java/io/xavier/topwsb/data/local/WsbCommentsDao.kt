package io.xavier.topwsb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.xavier.topwsb.common.TABLE_NAME_COMMENTS
import io.xavier.topwsb.common.TICKER_STOCK_COL_NAME
import io.xavier.topwsb.domain.model.WsbComment

@Dao
interface WsbCommentsDao {
    /**
     * Inserts a list of [WsbComment] objects for caching.
     *
     * @param comments List of [WsbComment]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<WsbComment>)

    /**
     * Retrieves all cached WsbComments that reference a ticker.
     *
     * @param ticker all cached comments referencing this ticker will be retrieved
     * @return list of [WsbComment]
     */
    @Query(
        """
            SELECT *
            FROM $TABLE_NAME_COMMENTS
            WHERE UPPER(:ticker) == $TICKER_STOCK_COL_NAME
        """
    )
    suspend fun getWsbComments(ticker: String): List<WsbComment>
}
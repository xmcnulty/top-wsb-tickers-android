package io.xavier.topwsb.data.local.repository

import io.xavier.topwsb.domain.model.TrendingStock
import retrofit2.HttpException
import java.io.IOException

/**
 * Interface for handling data of trending stocks.
 */
interface TrendingStockRepository {

    /**
     * Returns a list of trending stocks on /r/wallstreetbets. If the local cache is empty,
     * a call to the remote api will be made.
     *
     * @return list of [TrendingStock] objects
     */
    @Throws(HttpException::class, IOException::class)
    suspend fun getTrendingStocks(): List<TrendingStock>

    /**
     * Refreshes the cache with data from remote api.
     *
     * @return stocks from newly refreshed cache
     */
    @Throws(HttpException::class, IOException::class)
    suspend fun refreshCache(): List<TrendingStock>
}
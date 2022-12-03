package io.xavier.topwsb.domain.repository

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
     * @throws HttpException there was an error with the remote API call
     * @throws IOException network error
     */
    @kotlin.jvm.Throws(HttpException::class, IOException::class)
    suspend fun getTrendingStocks(): List<TrendingStock>
}
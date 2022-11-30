package io.xavier.topwsb.domain.repository
import retrofit2.HttpException
import java.io.IOException
import io.xavier.topwsb.domain.model.StockOverview

interface StockOverviewRepository {
    /**
     * Returns basic stock information for a stock. If stock information cannot be found on
     * storage, an attempt to get the data (and cache it) will be made.
     *
     * @param ticker ticker of the stock to query
     * @return [StockOverview]
     * @throws HttpException
     * @throws IOException
     */
    suspend fun getStockOverview(ticker: String): StockOverview
}
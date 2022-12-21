package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.domain.exceptions.ApiException
import io.xavier.topwsb.domain.model.chart_data.IntradayData

/**
 * Data repository for handling intraday time-series data operations
 *
 * @author xmcnulty
 */
interface IntradayDataRepository {
    /**
     * Fetches intraday time-series data for [ticker]. Fetches data from cache. If there is no
     * data in the cache, then the cache is refreshed with data from remote api.
     *
     * Currently only get time-series in 30min intervals
     *
     * @param ticker stock ticker
     * @return [IntradayData]
     *
     * @throws [ApiException] if Api response contains no intraday data
     */
    @Throws(ApiException::class)
    suspend fun getIntradayData(ticker: String): IntradayData
}
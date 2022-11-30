package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.trending_stocks.TrendingStockDto
import io.xavier.topwsb.domain.model.TrendingStock

/**
 * Interface for handling data of trending stocks.
 */
interface TrendingStockRepository {

    /**
     * Gets a list of
     */
    suspend fun getTrendingStocks(): List<TrendingStock>

    suspend fun getUpdatedTrendingStocks(): List<TrendingStockDto>

    suspend fun clearTrendingStockCache()

    suspend fun insertTrendingStocks(stocks: List<TrendingStock>)
}
package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.trending_stocks.TrendingStockDto

interface TrendingStockRepository {

    suspend fun getTrendingStocks(): List<TrendingStockDto>
}
package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.TrendingStockDto

interface TrendingStockRepository {

    suspend fun getTrendingStocks(): List<TrendingStockDto>
}
package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.StockDto

interface TrendingStockRepository {

    suspend fun getTrendingStocks(): List<StockDto>
}
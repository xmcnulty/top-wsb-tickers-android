package io.xavier.topwsb.data.repository

import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.remote.TrendingStockApi
import io.xavier.topwsb.data.remote.dto.StockDto
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingStockRepositoryImpl @Inject constructor(
    private val trendingApi: TrendingStockApi,
    private val db: TrendingStockDatabase
) : TrendingStockRepository {

    private val dao = db.dao

    override suspend fun getTrendingStocks(): List<StockDto> {
        return trendingApi.getStocks()
    }
}
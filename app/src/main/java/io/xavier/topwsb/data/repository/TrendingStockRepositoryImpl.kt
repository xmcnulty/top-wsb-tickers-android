package io.xavier.topwsb.data.repository

import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.remote.TrendingStockApi
import io.xavier.topwsb.domain.mapper.toTrendingStock
import io.xavier.topwsb.domain.model.TrendingStock
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingStockRepositoryImpl @Inject constructor(
    private val trendingApi: TrendingStockApi,
    db: TrendingStockDatabase
) : TrendingStockRepository {

    val tag = "TRENDING_STOCK_REPOSITORY"

    private val dao = db.dao

    override suspend fun getTrendingStocks(): List<TrendingStock> {
        val stocks = dao.getTrendingStocks()

        if (stocks.isNotEmpty()) {
            return stocks
        }

        return refreshCache()
    }

    override suspend fun refreshCache(): List<TrendingStock> {
        val stocks = trendingApi.getStocks()

        val currentTime = System.currentTimeMillis()

        dao.insertTrendingStocks(stocks.map {
            it.toTrendingStock(currentTime)
        })

        return dao.getTrendingStocks()
    }

    override suspend fun getTrendingStock(ticker: String): TrendingStock? {
        return dao.getTrendingStock(ticker).getOrNull(0)
    }
}
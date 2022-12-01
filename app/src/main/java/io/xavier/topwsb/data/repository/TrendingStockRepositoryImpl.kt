package io.xavier.topwsb.data.repository

import android.util.Log
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

        Log.d(tag, "Number of stocks in cache: ${stocks.size}")

        return stocks.ifEmpty {
            Log.d(tag, "Fetching trending stocks from remote")

            val currentTime = System.currentTimeMillis()

            val result = trendingApi.getStocks().map { it.toTrendingStock(currentTime) }

            dao.insertTrendingStocks(result)

            result
        }
    }
}
package io.xavier.topwsb.data.repository

import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.local.entities.TrendingStockEntity
import io.xavier.topwsb.data.remote.TrendingStockApi
import io.xavier.topwsb.domain.model.TrendingStock
import io.xavier.topwsb.data.local.repository.TrendingStockRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingStockRepositoryImpl @Inject constructor(
    private val trendingApi: TrendingStockApi,
    db: TrendingStockDatabase
) : TrendingStockRepository {
    private val dao = db.dao

    override suspend fun getTrendingStocks(): List<TrendingStock> {
        val stocks = dao.getTrendingStocks()

        if (stocks.isNotEmpty()) {
            return stocks.map { entity ->
                TrendingStock.fromEntity(entity)
            }
        }

        return refreshCache()
    }

    override suspend fun refreshCache(): List<TrendingStock> {
        val stocks = trendingApi.getStocks()

        val currentTime = System.currentTimeMillis()

        dao.insertTrendingStocks(stocks.map { dto ->
            TrendingStockEntity.fromDto(
                dto,
                currentTime
            )
        })

        return dao.getTrendingStocks().map { entity ->
            TrendingStock.fromEntity(entity)
        }
    }
}
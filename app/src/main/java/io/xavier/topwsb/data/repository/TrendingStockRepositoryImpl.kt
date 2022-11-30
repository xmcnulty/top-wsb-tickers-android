package io.xavier.topwsb.data.repository

import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.remote.TrendingStockApi
import io.xavier.topwsb.data.remote.dto.trending_stocks.TrendingStockDto
import io.xavier.topwsb.domain.model.TrendingStock
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingStockRepositoryImpl @Inject constructor(
    private val trendingApi: TrendingStockApi,
    db: TrendingStockDatabase
) : TrendingStockRepository {

    private val dao = db.dao

    /**
     * Gets a list of trending stocks from the tradestie api and returns a list of
     * [TrendingStockDto] of stocks with at least five comments.
     *
     * @return list of [TrendingStockDto] with no_of_comments >= 5
     */
    override suspend fun getTrendingStocks(): List<TrendingStock> {
        return dao.getTrendingStocks()
    }

    override suspend fun getUpdatedTrendingStocks(): List<TrendingStockDto> {
        return trendingApi.getStocks()
    }

    /**
     * Clears all trending stocks in the database
     */
    override suspend fun clearTrendingStockCache() {
        dao.clearTrendingStocks()
    }

    /**
     * Inserts a new list of stocks into the database.
     *
     * @param stocks list of [TrendingStock]s to insert
     */
    override suspend fun insertTrendingStocks(stocks: List<TrendingStock>) {
        dao.insertTrendingStocks(stocks)
    }
}
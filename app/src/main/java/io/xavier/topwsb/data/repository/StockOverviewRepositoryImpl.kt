package io.xavier.topwsb.data.repository

import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.remote.StockDataApi
import io.xavier.topwsb.domain.model.StockOverview
import io.xavier.topwsb.domain.repository.StockOverviewRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockOverviewRepositoryImpl @Inject constructor(
    private val stockDataApi: StockDataApi,
    database: TrendingStockDatabase
) : StockOverviewRepository {

    private val dao = database.dao

    /**
     * Queries database for stock overview for [ticker].
     *
     * @param ticker stock to query
     * @return [StockOverview] if found, null otherwise
     */
    override suspend fun getStockOverview(ticker: String): StockOverview? {
        val result = dao.getStockOverview(ticker)

        return if (result.isEmpty())
            null
        else
            result[0]
    }
}
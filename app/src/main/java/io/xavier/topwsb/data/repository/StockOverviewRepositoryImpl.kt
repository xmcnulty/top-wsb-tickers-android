package io.xavier.topwsb.data.repository

import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.remote.StockDataApi
import io.xavier.topwsb.domain.mapper.toStockOverview
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

    override suspend fun getStockOverview(ticker: String): StockOverview {
        val result = dao.getStockOverview(ticker)

        return if (result.isEmpty()) {
            val stockOverview = stockDataApi.getStockOverview(
                apiKey = BuildConfig.API_KEY_ALPHA_ADVANTAGE,
                ticker = ticker
            ).toStockOverview()

            dao.insertStockOverview(stockOverview)

            stockOverview
        } else
            result[0]
    }
}
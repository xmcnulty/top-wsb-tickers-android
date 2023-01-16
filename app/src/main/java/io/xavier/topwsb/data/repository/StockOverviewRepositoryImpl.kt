package io.xavier.topwsb.data.repository

import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.local.TrendiesDatabase
import io.xavier.topwsb.data.remote.StockDataApi
import io.xavier.topwsb.domain.exceptions.ApiException
import io.xavier.topwsb.domain.mapper.toStockOverview
import io.xavier.topwsb.domain.model.MarketData
import io.xavier.topwsb.domain.repository.StockOverviewRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockOverviewRepositoryImpl @Inject constructor(
    private val stockDataApi: StockDataApi,
    database: TrendiesDatabase
) : StockOverviewRepository {

    private val dao = database.stockOverviewDao

    override suspend fun getStockOverview(ticker: String): MarketData {
        val result = dao.getStockOverview(ticker)

        return if (result.isEmpty()) {
            try {
                val stockOverview = stockDataApi.getStockOverview(
                    apiKey = BuildConfig.API_KEY_ALPHA_ADVANTAGE,
                    ticker = ticker
                ).toStockOverview()

                dao.insertStockOverview(stockOverview)

                stockOverview
            } catch (e: Exception) {
                throw ApiException()
            }
        } else {
            result[0]
        }
    }
}
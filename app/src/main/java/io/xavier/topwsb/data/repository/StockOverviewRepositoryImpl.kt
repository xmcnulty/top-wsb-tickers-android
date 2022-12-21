package io.xavier.topwsb.data.repository

import android.util.Log
import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.remote.StockDataApi
import io.xavier.topwsb.domain.mapper.toStockOverview
import io.xavier.topwsb.domain.model.MarketData
import io.xavier.topwsb.domain.repository.StockOverviewRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockOverviewRepositoryImpl @Inject constructor(
    private val stockDataApi: StockDataApi,
    database: TrendingStockDatabase
) : StockOverviewRepository {

    val tag = "STOCK OVERVIEW REPOSITORY"

    private val dao = database.dao

    override suspend fun getStockOverview(ticker: String): MarketData {
        val result = dao.getStockOverview(ticker)

        return if (result.isEmpty()) {
            Log.d(tag, "Fetching $ticker overview from remote")

            val stockOverview = stockDataApi.getStockOverview(
                apiKey = BuildConfig.API_KEY_ALPHA_ADVANTAGE,
                ticker = ticker
            ).toStockOverview()

            dao.insertStockOverview(stockOverview)

            stockOverview
        } else {
            Log.d(tag, "Fetched $ticker overview from cache")
            result[0]
        }
    }
}
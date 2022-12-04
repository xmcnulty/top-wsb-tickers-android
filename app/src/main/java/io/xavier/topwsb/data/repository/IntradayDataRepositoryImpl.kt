package io.xavier.topwsb.data.repository

import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.remote.StockDataApi
import io.xavier.topwsb.domain.exceptions.ApiException
import io.xavier.topwsb.domain.mapper.toIntradayData
import io.xavier.topwsb.domain.model.chart_data.IntradayData
import io.xavier.topwsb.domain.repository.IntradayDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntradayDataRepositoryImpl @Inject constructor(
    private val stockDataApi: StockDataApi
) : IntradayDataRepository {
    override suspend fun getIntradayData(ticker: String): IntradayData {
        val data = stockDataApi.getIntradayData(
            apiKey = BuildConfig.API_KEY_ALPHA_ADVANTAGE,
            ticker = ticker
        )

        if (data.timeSeries.isNullOrEmpty()) {
            throw ApiException("Unable to fetch timeseries data.")
        }

        return data.toIntradayData()
    }
}
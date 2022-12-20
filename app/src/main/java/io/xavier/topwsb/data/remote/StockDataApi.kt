package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.company_overview.StockOverviewDto
import io.xavier.topwsb.data.remote.dto.intraday_data.IntradayDataDto
import io.xavier.topwsb.domain.model.chart_data.IntradayInterval
import retrofit2.http.GET
import retrofit2.http.Query

interface StockDataApi {
    @GET("query?function=OVERVIEW")
    suspend fun getStockOverview(
        @Query("apikey") apiKey: String,
        @Query("symbol") ticker: String
    ): StockOverviewDto

    @GET("query?function=TIME_SERIES_INTRADAY&outputsize=compact")
    suspend fun getIntradayData(
        @Query("interval") interval: IntradayInterval,
        @Query("apikey") apiKey: String,
        @Query("symbol") ticker: String
    ): IntradayDataDto
}
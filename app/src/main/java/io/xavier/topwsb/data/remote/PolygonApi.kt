package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.ticker_detail.TickerDetailDto
import io.xavier.topwsb.data.remote.dto.intraday_data.IntradayDataDto
import io.xavier.topwsb.domain.model.chart_data.IntradayInterval
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PolygonApi {
    @GET("/v3/reference/tickers/{ticker}")
    suspend fun getTickerDetails(
        @Path("ticker") ticker: String,
        @Query("apiKey") apiKey: String
    ): TickerDetailDto?

    @GET("query?function=TIME_SERIES_INTRADAY&outputsize=compact")
    suspend fun getIntradayData(
        @Query("interval") interval: IntradayInterval,
        @Query("apikey") apiKey: String,
        @Query("symbol") ticker: String
    ): IntradayDataDto
}
package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.chart_data.ChartDataDto
import io.xavier.topwsb.data.remote.dto.ticker_detail.TickerDetailDto
import io.xavier.topwsb.domain.repository.chart_data.TimeSpan
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PolygonApi {
    /**
     * Gets information about a ticker by making a call to the Ticker Details V3 endpoint
     * of Polygon.
     *
     * @param ticker ticker of stock to be queried
     * @param apiKey Polygon API key
     */
    @GET("/v3/reference/tickers/{ticker}")
    suspend fun getTickerDetails(
        @Path("ticker") ticker: String,
        @Query("apiKey") apiKey: String
    ): TickerDetailDto?

    /**
     * Gets price data for charting by making a call to the Aggregates (Bars) endpoint of Polygon.
     *
     * @param ticker ticker symbol of stock/equity
     * @param multiplier size of the timespan multiplier
     * @param timespan size of the time window (bar). Valid entries are minute, hour, day, week,
     * month, quarter, year
     * @param from start of the aggregate time window in date format YYYY-MM-DD
     * @param to end of aggregate time window in date format YYYY-MM-DD
     * @param adjusted if true, results are adjusted for splits
     * @param sort
     * @param apiKey Polygon API key
     */
    @GET("/v2/aggs/ticker/{ticker}/range/{multiplier}/{timespan}/{from}/{to}")
    suspend fun getChartData(
       @Path("ticker") ticker: String,
       @Path("multiplier") multiplier: Int,
       @Path("timespan") timespan: TimeSpan,
       @Path("from") from: String,
       @Path("to") to: String,
       @Query("adjusted") adjusted: Boolean = true,
       @Query("sort") sort: String = "asc",
       @Query("limit") limit: Int = 50_000,
       @Query("apiKey") apiKey: String
    ): ChartDataDto
}
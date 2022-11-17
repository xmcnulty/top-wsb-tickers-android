package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.StockHistoryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface YahooFinanceApi {

    @GET("stock/history/{symbol}/{interval}")
    suspend fun getStockHistory(
        @Path("symbol") symbol: String,
        @Path("interval") interval: String
    ): StockHistoryDto
}
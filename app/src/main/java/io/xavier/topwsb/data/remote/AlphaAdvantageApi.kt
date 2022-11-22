package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.StockDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaAdvantageApi {
    @GET("query?function=OVERVIEW")
    suspend fun getStockDetails(
        @Query("apikey") apiKey: String,
        @Query("symbol") symbol: String
    ): StockDetailDto
}
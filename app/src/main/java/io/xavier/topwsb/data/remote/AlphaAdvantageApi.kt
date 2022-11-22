package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.StockDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlphaAdvantageApi {
    @GET("query?function=OVERVIEW&symbol={symbol}")
    suspend fun getStonkDetail(
        @Query("apikey") apiKey: String,
        @Path("symbol") symbol: String
    ): StockDetailDto
}
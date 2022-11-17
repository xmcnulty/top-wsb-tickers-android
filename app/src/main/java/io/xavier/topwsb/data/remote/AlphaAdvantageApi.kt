package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.StockDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AlphaAdvantageApi {
    @GET("function=GLOBAL_QUOTES&symbol={symbol}&apikey={apikey}")
    suspend fun getStonkDetail(
        @Path("symbol") symbol: String,
        @Path("apikey") apikey: String
    ): StockDetailDto
}
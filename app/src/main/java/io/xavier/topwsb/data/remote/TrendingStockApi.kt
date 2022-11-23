package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.StockDto
import retrofit2.http.GET

interface TrendingStockApi {

    @GET("v1/apps/reddit")
    suspend fun getStocks(): List<StockDto>
}
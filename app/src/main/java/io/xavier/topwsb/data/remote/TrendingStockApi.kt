package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.TrendingStockDto
import retrofit2.http.GET

interface TrendingStockApi {

    @GET("v1/apps/reddit")
    suspend fun getStocks(): List<TrendingStockDto>
}
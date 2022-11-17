package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.StonksDto
import retrofit2.http.GET

interface TradestieRedditApi {

    @GET("v1/apps/reddit")
    suspend fun getStonks(): List<StonksDto>
}
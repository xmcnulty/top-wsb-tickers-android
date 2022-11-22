package io.xavier.topwsb.data.repository

import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.remote.AlphaAdvantageApi
import io.xavier.topwsb.data.remote.TradestieRedditApi
import io.xavier.topwsb.data.remote.dto.StockDetailDto
import io.xavier.topwsb.data.remote.dto.StockDto
import io.xavier.topwsb.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val wsbApi: TradestieRedditApi,
    private val alphaAdvApi: AlphaAdvantageApi
) : StockRepository {
    override suspend fun getStocks(): List<StockDto> {
        return wsbApi.getStocks()
    }

    override suspend fun getStockDetail(symbol: String): StockDetailDto {
        return alphaAdvApi.getStockDetails(
            apiKey = BuildConfig.API_KEY_ALPHA_ADVANTAGE,
            symbol = symbol
        )
    }
}
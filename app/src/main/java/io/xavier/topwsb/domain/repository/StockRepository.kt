package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.StockDetailDto
import io.xavier.topwsb.data.remote.dto.StockDto

interface StockRepository {

    suspend fun getStocks(): List<StockDto>

    suspend fun getStockDetail(symbol: String): StockDetailDto
}
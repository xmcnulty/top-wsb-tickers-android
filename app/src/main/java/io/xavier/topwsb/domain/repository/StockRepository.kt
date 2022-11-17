package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.StockDetailDto
import io.xavier.topwsb.data.remote.dto.StockDto

interface StockRepository {

    suspend fun getStonks(): List<StockDto>

    suspend fun getStonkDetail(symbol: String): StockDetailDto
}
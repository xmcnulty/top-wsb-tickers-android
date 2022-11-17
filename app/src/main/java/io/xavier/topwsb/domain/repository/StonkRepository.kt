package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.StonkDetailDto
import io.xavier.topwsb.data.remote.dto.StonksDto

interface StonkRepository {

    suspend fun getStonks(): List<StonksDto>

    suspend fun getStonkDetail(symbol: String): StonkDetailDto
}
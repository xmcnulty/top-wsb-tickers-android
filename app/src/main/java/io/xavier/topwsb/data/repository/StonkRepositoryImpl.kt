package io.xavier.topwsb.data.repository

import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.remote.AlphaAdvantageApi
import io.xavier.topwsb.data.remote.TradestieRedditApi
import io.xavier.topwsb.data.remote.dto.StonkDetailDto
import io.xavier.topwsb.data.remote.dto.StonksDto
import io.xavier.topwsb.domain.repository.StonkRepository
import javax.inject.Inject

class StonkRepositoryImpl @Inject constructor(
    private val wsbApi: TradestieRedditApi,
    private val alphaAdvApi: AlphaAdvantageApi
) : StonkRepository {
    override suspend fun getStonks(): List<StonksDto> {
        return wsbApi.getStonks()
    }

    override suspend fun getStonkDetail(symbol: String): StonkDetailDto {
        return alphaAdvApi.getStonkDetail(
            symbol,
            BuildConfig.API_KEY_ALPHA_ADVANTAGE
        )
    }
}
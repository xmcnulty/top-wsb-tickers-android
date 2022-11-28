package io.xavier.topwsb.data.repository

import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.remote.StockDataApi
import io.xavier.topwsb.data.remote.dto.company_overview.CompanyOverviewDto
import io.xavier.topwsb.domain.repository.StockDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockDataRepositoryImpl @Inject constructor(
    private val stockDataApi: StockDataApi
) : StockDataRepository {

    override suspend fun getCompanyOverview(ticker: String): CompanyOverviewDto {
        return stockDataApi.getStockDetails(
            apiKey = BuildConfig.API_KEY_ALPHA_ADVANTAGE,
            symbol = ticker
        )
    }
}
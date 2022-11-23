package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.CompanyOverviewDto

interface StockDataRepository {
    suspend fun getCompanyOverview(ticker: String): CompanyOverviewDto
}
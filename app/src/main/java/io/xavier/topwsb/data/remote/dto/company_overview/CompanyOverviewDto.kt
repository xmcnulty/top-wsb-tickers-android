package io.xavier.topwsb.data.remote.dto.company_overview

import io.xavier.topwsb.domain.model.CompanyOverview

/**
 * Data class containing the fields returned from Alpha Advantage API OVERVIEW function call.
 * Data is returned from API in JSON format, and parsed by GSON.
 */
data class CompanyOverviewDto(
    val `200DayMovingAverage`: String,
    val `50DayMovingAverage`: String,
    val `52WeekHigh`: String,
    val `52WeekLow`: String,
    val Address: String,
    val AnalystTargetPrice: String,
    val AssetType: String,
    val Beta: String,
    val BookValue: String,
    val CIK: String,
    val Country: String,
    val Currency: String,
    val Description: String,
    val DilutedEPSTTM: String,
    val DividendDate: String,
    val DividendPerShare: String,
    val DividendYield: String,
    val EBITDA: String,
    val EPS: String,
    val EVToEBITDA: String,
    val EVToRevenue: String,
    val ExDividendDate: String,
    val Exchange: String,
    val FiscalYearEnd: String,
    val ForwardPE: String,
    val GrossProfitTTM: String,
    val Industry: String,
    val LatestQuarter: String,
    val MarketCapitalization: String,
    val Name: String,
    val OperatingMarginTTM: String,
    val PEGRatio: String,
    val PERatio: String,
    val PriceToBookRatio: String,
    val PriceToSalesRatioTTM: String,
    val ProfitMargin: String,
    val QuarterlyEarningsGrowthYOY: String,
    val QuarterlyRevenueGrowthYOY: String,
    val ReturnOnAssetsTTM: String,
    val ReturnOnEquityTTM: String,
    val RevenuePerShareTTM: String,
    val RevenueTTM: String,
    val Sector: String,
    val SharesOutstanding: String,
    val Symbol: String,
    val TrailingPE: String
)

/**
 * Converts a [CompanyOverviewDto] to [CompanyOverview] with required information for application use.
 *
 * @return [CompanyOverview] from data contained in this Dto object
 */
fun CompanyOverviewDto.toCompanyOverview(): CompanyOverview = CompanyOverview(
    ticker = Symbol,
    companyName = Name,
    ma50 = `50DayMovingAverage`,
    ma200 = `200DayMovingAverage`,
    high52Week = `52WeekHigh`,
    low52Week = `52WeekLow`,
    analystTargetPrice = AnalystTargetPrice
)
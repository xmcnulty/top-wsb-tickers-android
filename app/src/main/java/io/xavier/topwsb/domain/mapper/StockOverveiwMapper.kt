package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.data.remote.dto.company_overview.StockOverviewDto
import io.xavier.topwsb.domain.model.StockOverview

fun StockOverview.toMap(): Map<String, String> = mapOf(
    "Name" to companyName,
    "Ticker" to ticker,
    "Analyst Target" to "$$analystTargetPrice",
    "52 Week High" to "$$high52Week",
    "52 Week Low" to "$$low52Week"
)

/**
 * Converts a [StockOverviewDto] to [StockOverview] with required information for application use.
 *
 * @return [StockOverview] from data contained in this Dto object
 */
fun StockOverviewDto.toStockOverview(): StockOverview = StockOverview(
    ticker = Symbol,
    companyName = Name,
    high52Week = `52WeekHigh`,
    low52Week = `52WeekLow`,
    analystTargetPrice = AnalystTargetPrice
)
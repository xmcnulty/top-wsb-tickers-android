package io.xavier.topwsb.domain.model

data class StockDetail(
    val ticker: String,
    val companyName: String,
    val ma50: String,
    val ma200: String,
    val high52Week: String,
    val low52Week: String,
    val analystTargetPrice: String
)

fun StockDetail.toMap(): Map<String, String> = mapOf(
    "Name" to companyName,
    "Ticker" to ticker,
    "Analyst Target" to analystTargetPrice,
    "52 Week High" to high52Week,
    "52 Week Low" to low52Week,
    "50 day MA" to ma50,
    "200 day MA" to ma200
)


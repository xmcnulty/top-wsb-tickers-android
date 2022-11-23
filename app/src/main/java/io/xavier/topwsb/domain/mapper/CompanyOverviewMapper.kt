package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.domain.model.CompanyOverview

fun CompanyOverview.toMap(): Map<String, String> = mapOf(
    "Name" to companyName,
    "Ticker" to ticker,
    "Analyst Target" to analystTargetPrice,
    "52 Week High" to high52Week,
    "52 Week Low" to low52Week,
    "50 day MA" to ma50,
    "200 day MA" to ma200
)
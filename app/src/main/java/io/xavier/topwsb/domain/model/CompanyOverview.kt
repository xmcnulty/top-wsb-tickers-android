package io.xavier.topwsb.domain.model

data class CompanyOverview(
    val ticker: String,
    val companyName: String,
    val ma50: String,
    val ma200: String,
    val high52Week: String,
    val low52Week: String,
    val analystTargetPrice: String
)


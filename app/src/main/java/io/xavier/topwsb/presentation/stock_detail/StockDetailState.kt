package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.domain.model.CompanyOverview

data class StockDetailState(
    val isLoading: Boolean = false,
    val companyOverview: CompanyOverview? = null,
    val error: String = ""
)

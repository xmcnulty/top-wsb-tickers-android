package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.domain.model.StockDetail

data class StockDetailState(
    val isLoading: Boolean = false,
    val stockDetail: StockDetail? = null,
    val error: String = ""
)

package io.xavier.topwsb.data.remote.dto

data class StockHistoryDto(
    val error: Any,
    val items: List<HistoryItem>,
    val meta: Meta
)
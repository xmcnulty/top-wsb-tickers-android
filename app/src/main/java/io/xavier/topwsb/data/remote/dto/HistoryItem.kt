package io.xavier.topwsb.data.remote.dto

data class HistoryItem(
    val date: String,
    val date_utc: Long,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Long
)

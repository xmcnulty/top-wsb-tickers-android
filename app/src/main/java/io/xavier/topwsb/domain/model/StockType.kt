package io.xavier.topwsb.domain.model

enum class StockType(
    val string: String
) {
    ETF("ETF"),
    COMMON_SHARE("Common Share"),
    UNKNOWN("Unknown");

    companion object {
        fun build(value: String): StockType = when(value) {
            "ETF" -> ETF
            "CS" -> COMMON_SHARE
            else -> UNKNOWN
        }
    }
}
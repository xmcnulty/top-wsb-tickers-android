package io.xavier.topwsb.domain.model.trending_stock

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
enum class StockType(
    val string: String
) : Parcelable {
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
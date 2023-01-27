package io.xavier.topwsb.domain.model.trending_stock

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
enum class StockType(
    val code: String,
    val description: String
) : Parcelable {
    ETF("ETF", "ETF"),
    COMMON_SHARE("CS", "Common Share"),
    PREFERRED_STOCK("PFD", "Preferred Stock"),
    WARRANT("WARRANT", "Warrant"),
    RIGHT("RIGHT", "Rights"),
    BOND("BOND", "Corporate Bond"),
    ETN("ETN", "Exchange Traded Note"),
    ETV("ETV", "Exchange Traded Vehicle"),
    SP("SP", "Structured Product"),
    ADRC("ADRC", "ADRC"),
    ADRP("ADRP", "ADRP"),
    ADRW("ADRW", "ADRW"),
    ADRR("ADRR", "ADRR"),
    FUND("FUND", "Fund"),
    BASKET("BASKET", "Basket"),
    UNIT("UNIT", "Unit"),
    LT("LT", "Liquidating Trust"),
    OS("OS", "Ordinary Share"),
    GDR("GDR", "Global Depository Receipts"),
    NYRS("NYRS", "NY Registry Shares"),
    AGEN("AGEN", "Agency Bond"),
    EQLK("EQLK", "Equity Linked Bond"),
    ETS("ETS", "Single-security ETF"),
    OTHER("OTHER", "Other Security Type");

    companion object {
        fun build(code: String): StockType {
            return StockType.values().firstOrNull { type ->
                type.code == code
            } ?: OTHER
        }
    }
}
package io.xavier.topwsb.presentation.stock_detail

/**
 * Contains the state of the price chart on the stock detail screen.
 *
 * @property loading true if loading data from api
 * @property errorMsg error message to display in chart area
 * @property data intra-day chart data
 */
data class ChartState(
    var loading: Boolean = false,
    var errorMsg: String = "",
    var data: Any? = null
)

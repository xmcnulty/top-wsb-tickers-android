package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.domain.model.StockOverview

/**
 * Contains the state of the market overview section on the stock detail screen.
 *
 * @property loading true if loading data from api
 * @property errorMsg error message to display in chart area
 * @property data Market overview data returned from the api
 */
data class MktOverviewState(
    var loading: Boolean = false,
    var errorMsg: String = "",
    var data: StockOverview? = null
)

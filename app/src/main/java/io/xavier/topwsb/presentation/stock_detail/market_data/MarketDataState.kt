package io.xavier.topwsb.presentation.stock_detail.market_data

import io.xavier.topwsb.domain.model.MarketData

/**
 * State of market data displayed on the Stock Detail screen.
 */
sealed interface MarketDataState {
    object Loading : MarketDataState
    data class Error(val message: String) : MarketDataState
    data class Success(val data: MarketData) : MarketDataState
}
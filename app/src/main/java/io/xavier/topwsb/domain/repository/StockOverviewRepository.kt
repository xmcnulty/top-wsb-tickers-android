package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.domain.model.StockOverview

interface StockOverviewRepository {
    /**
     * Returns basic stock information for a stock.
     *
     * @param ticker ticker of the stock to query
     * @return [StockOverview] if overview information found, null if not
     */
    suspend fun getStockOverview(ticker: String): StockOverview?
}
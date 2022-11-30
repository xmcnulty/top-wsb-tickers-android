package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.data.remote.dto.trending_stocks.TrendingStockDto
import io.xavier.topwsb.domain.model.TrendingStock

/**
 * Maps a [TrendingStockDto] object to a [TrendingStock] for local storage.
 *
 * @param updated time the list of trending stocks was updated
 * @return mapped [TrendingStock]
 */
fun TrendingStockDto.toTrendingStock(updated: Long) = TrendingStock(
    ticker = this.ticker.uppercase(), // tickers in all caps
    lastUpdatedUtc = updated,
    numberOfComments = this.no_of_comments,
    sentiment = this.sentiment,
    sentimentScore = this.sentiment_score
)
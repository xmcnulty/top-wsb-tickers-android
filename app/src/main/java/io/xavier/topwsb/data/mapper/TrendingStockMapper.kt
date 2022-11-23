package io.xavier.topwsb.data.mapper

import io.xavier.topwsb.data.local.TrendingStockEntity
import io.xavier.topwsb.domain.model.TrendingStock

fun TrendingStockEntity.toTrendingStock(): TrendingStock {
    return TrendingStock(
        numberOfComments = numberOfComments,
        sentiment = sentiment,
        sentimentScore = sentimentScore,
        ticker = ticker
    )
}

fun TrendingStock.toTrendingStockEntity(): TrendingStockEntity {
    return TrendingStockEntity(
        numberOfComments = numberOfComments,
        sentiment = sentiment,
        sentimentScore = sentimentScore,
        ticker = ticker
    )
}
package io.xavier.topwsb.data.repository

import android.util.Log
import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.local.TrendiesDatabase
import io.xavier.topwsb.data.remote.PolygonApi
import io.xavier.topwsb.data.remote.TrendingStockApi
import io.xavier.topwsb.data.remote.dto.ticker_detail.TickerDetailDto
import io.xavier.topwsb.data.remote.dto.trending_stocks.TrendingStockDto
import io.xavier.topwsb.domain.model.Sentiment
import io.xavier.topwsb.domain.model.trending_stock.StockType
import io.xavier.topwsb.domain.model.trending_stock.TrendingStock
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingStockRepositoryImpl @Inject constructor(
    private val trendingApi: TrendingStockApi,
    private val polygonApi: PolygonApi,
    db: TrendiesDatabase
) : TrendingStockRepository {
    private val dao = db.trendingStockDao

    override suspend fun getTrendingStocks(): List<TrendingStock> {
        val stocks = dao.getTrendingStocks()

        return if (stocks.isNotEmpty()) {
            stocks.map { entity ->
                TrendingStock.build(entity)
            }
        } else {
            val trendingStocks = getRemoteStocks()

            dao.insertTrendingStocks(
                trendingStocks.map {trendingStock ->
                    trendingStock.toEntity()
                }
            )

            trendingStocks
        }
    }

    override suspend fun refreshTrendingStocks(): List<TrendingStock> {
        val stocks = getRemoteStocks()

        dao.clearTrendingStocks()

        dao.insertTrendingStocks(
            stocks.map { trendingStock ->
                trendingStock.toEntity()
            }
        )

        return stocks
    }

    /**
     * Fetches list of trending stocks along with their ticker data.
     *
     * @return list of [TrendingStock]
     */
    private suspend fun getRemoteStocks(): List<TrendingStock> {
        val currentTime = System.currentTimeMillis()

        val trendingStocks = trendingApi.getStocks().take(20)
        val details = getDetails(trendingStocks).filter {
            it.second != null
        }

        print(details)

        return details.map {
            TrendingStock(
                ticker = it.first.ticker,
                name = it.second!!.results!!.name,
                type = StockType.build(it.second!!.results!!.type),
                marketCap = it.second!!.results!!.market_cap,
                sharesOutstanding = it.second!!.results!!.share_class_shares_outstanding,
                logoUrl = it.second!!.results!!.branding?.logo_url,
                lastUpdatedUtc = currentTime,
                sentiment = Sentiment.fromName(it.first.sentiment),
                sentimentScore = it.first.sentiment_score,
                commentCount = it.first.no_of_comments
            )
        }
    }

    private suspend fun getDetails(
        stocks: List<TrendingStockDto>
    ): List<Pair<TrendingStockDto, TickerDetailDto?>> {
        val details = coroutineScope {
            stocks.map {
                val details = try {
                    polygonApi.getTickerDetails(
                        ticker = it.ticker,
                        apiKey = BuildConfig.API_KEY_POLYGON
                    )
                } catch (e: Exception) {
                    null
                }

                Log.d("Getting Details", details?.status ?: "Error")

                it to details
            }
        }

        return details
    }

}
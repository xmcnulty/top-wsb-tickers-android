package io.xavier.topwsb.domain.use_case.stock_list

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.common.isOutdated
import io.xavier.topwsb.domain.mapper.toTrendingStock
import io.xavier.topwsb.domain.model.RefreshTimes
import io.xavier.topwsb.domain.model.TrendingStock
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case to fetch the trending stocks on wallstreetbets. Always returns a list fetched
 * from the local database, which is the single source of truth for this data.
 *
 * @property repository [TrendingStockRepository] to fetch list of stocks from
 */
class GetTrendingStocksUseCase @Inject constructor(
    private val repository: TrendingStockRepository
) {
    /**
     * Fetches a list of trending stocks from the local cache. If the local cache is empty or
     * contains outdated data. An attempt to refresh the data from a remote source will be made.
     * Because the local cache acts as a single source of truth the following steps will be followed
     * when refreshing data:
     *
     *  1. Clears the current cache.
     *  2. Attempts to fetch an updated remote list.
     *  3. Saves the remote list to the cache.
     *  4. Returns the updated list from the cache.
     *
     * @param refreshTime time interval that determines if data is outdated or not
     * @return list of [TrendingStock] fetched from the Room database
     */
    operator fun invoke(refreshTime: RefreshTimes = RefreshTimes.ONE_HOUR):
            Flow<Resource<List<TrendingStock>>> = flow {
        emit(Resource.Loading())

        // get all the stocks from local storage (single source of truth)
        val stocks = repository.getTrendingStocks()

        // emit stocks and return if local storage contains up-to-date data
        if (stocks.isNotEmpty() && stocks[0].isOutdated(refreshTime)) {
            emit(Resource.Success(stocks))

            return@flow
        }

        repository.clearTrendingStockCache() // clear the current cache

        // need to get updated data from remote source
        val updatedStocks = try {
            repository.getUpdatedTrendingStocks().filter { stock ->
                stock.no_of_comments >= 5
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            return@flow
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
            return@flow
        }

        val currentTime = System.currentTimeMillis()

        // load new data to cache with current time as last updated time
        repository.insertTrendingStocks(
            updatedStocks.map {
                it.toTrendingStock(currentTime)
            }
        )

        // load and return updated stocks from cache (single source of truth)
        emit(Resource.Success(
            repository.getTrendingStocks()
        ))
    }
}
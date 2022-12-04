package io.xavier.topwsb.domain.use_case.stock_details

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.domain.model.TrendingStock
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case to get a [TrendingStock] from the cache.
 *
 * @property repository [TrendingStockRepository] to get data from
 */
class GetTrendingStockUseCase @Inject constructor(
    private val repository: TrendingStockRepository
) {
    /**
     * Fetches a [TrendingStock] from [repository]
     */
    operator fun invoke(ticker: String): Flow<Resource<TrendingStock>> = flow {
        emit(Resource.Loading())

        repository.getTrendingStock(ticker)?.let { stock ->
            emit(Resource.Success(stock))

            return@flow
        }

        emit(Resource.Error("Cannot retrieve stock"))
    }
}
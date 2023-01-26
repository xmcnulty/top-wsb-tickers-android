package io.xavier.topwsb.domain.use_case.stock_list

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.repository.exceptions.APIException
import io.xavier.topwsb.domain.model.trending_stock.TrendingStock
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
     * Fetches a list of trending stocks from [repository]
     *
     * @return list of [TrendingStock] fetched from the Room database
     */
    operator fun invoke(): Flow<Resource<List<TrendingStock>>> = flow {
        emit(Resource.Loading())

        try {
            val stocks = repository.getTrendingStocks()

            emit(Resource.Success(stocks))
        } catch (e: APIException) {
            emit(Resource.Error(e.errorResId))
        }
    }
}
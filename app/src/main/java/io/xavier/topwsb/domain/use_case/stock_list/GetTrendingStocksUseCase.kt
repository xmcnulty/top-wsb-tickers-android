package io.xavier.topwsb.domain.use_case.stock_list

import io.xavier.topwsb.common.Resource
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
     * Fetches a list of trending stocks from [repository]
     *
     * @return list of [TrendingStock] fetched from the Room database
     */
    operator fun invoke(): Flow<Resource<List<TrendingStock>>> = flow {
        emit(Resource.Loading())

        try {
            val stocks = repository.getTrendingStocks()

            emit(Resource.Success(stocks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
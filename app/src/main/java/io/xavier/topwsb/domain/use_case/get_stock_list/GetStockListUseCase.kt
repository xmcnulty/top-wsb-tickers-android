package io.xavier.topwsb.domain.use_case.get_stock_list

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.remote.dto.trending_stocks.toStock
import io.xavier.topwsb.domain.model.TrendingStock
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(
    private val repository: TrendingStockRepository
) {
    operator fun invoke(): Flow<Resource<List<TrendingStock>>> = flow {
        try {
            emit(Resource.Loading())
            val stocks = repository.getTrendingStocks().map {
                it.toStock()
            }
            emit(Resource.Success(stocks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
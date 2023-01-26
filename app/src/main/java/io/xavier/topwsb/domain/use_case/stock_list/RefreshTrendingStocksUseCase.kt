package io.xavier.topwsb.domain.use_case.stock_list

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.repository.exceptions.APIException
import io.xavier.topwsb.domain.model.trending_stock.TrendingStock
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshTrendingStocksUseCase @Inject constructor(
    private val repository: TrendingStockRepository
) {
    operator fun invoke(): Flow<Resource<List<TrendingStock>>> = flow {

        emit(Resource.Loading())

        try {
            val stocks = repository.refreshTrendingStocks()

            emit(Resource.Success(stocks))
        } catch (e: APIException) {
            emit(Resource.Error(e.errorResId))
        }
    }
}
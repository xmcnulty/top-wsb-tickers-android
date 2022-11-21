package io.xavier.topwsb.domain.use_case.get_stock_list

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.remote.dto.toStock
import io.xavier.topwsb.domain.model.Stock
import io.xavier.topwsb.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(
    private val repository: StockRepository
) {
    operator fun invoke(): Flow<Resource<List<Stock>>> = flow {
        try {
            emit(Resource.Loading())
            val stocks = repository.getStocks().map {
                it.toStock()
            }.subList(0, 19)
            emit(Resource.Success(stocks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
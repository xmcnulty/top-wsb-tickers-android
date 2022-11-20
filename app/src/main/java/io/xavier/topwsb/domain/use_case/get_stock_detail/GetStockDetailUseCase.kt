package io.xavier.topwsb.domain.use_case.get_stock_detail

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.remote.dto.toStockDetail
import io.xavier.topwsb.domain.model.StockDetail
import io.xavier.topwsb.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStockDetailUseCase @Inject constructor(
    private val repository: StockRepository
) {
    operator fun invoke(symbol: String): Flow<Resource<StockDetail>> = flow {
        try {
            emit(Resource.Loading())
            val stonk = repository.getStockDetail(symbol).toStockDetail()
            emit(Resource.Success(stonk))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
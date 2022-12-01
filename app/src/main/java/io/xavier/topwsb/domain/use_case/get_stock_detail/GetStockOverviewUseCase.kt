package io.xavier.topwsb.domain.use_case.get_stock_detail

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.domain.model.StockOverview
import io.xavier.topwsb.domain.repository.StockOverviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStockOverviewUseCase @Inject constructor(
    private val repository: StockOverviewRepository
) {
    /**
     * Fetches [StockOverview] for given ticker.
     *
     * @param ticker ticker of stock to fetch info for
     * @return flow with a [Resource] that wraps a [StockOverview] object if successful
     */
    operator fun invoke(ticker: String): Flow<Resource<StockOverview>> = flow {
        emit(Resource.Loading())
        try {
            val stock = repository.getStockOverview(ticker)
            emit(Resource.Success(stock))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: NullPointerException) {
            // TODO: A null pointer exception means the ticker doesn't map to a known stock
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
package io.xavier.topwsb.domain.use_case.stock_details

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.domain.exceptions.ApiException
import io.xavier.topwsb.domain.model.chart_data.IntradayData
import io.xavier.topwsb.domain.model.chart_data.IntradayInterval
import io.xavier.topwsb.data.local.repository.IntradayDataRepository
import retrofit2.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * Fetches intraday data.
 */
class GetIntradayDataUseCase @Inject constructor(
    private val repository: IntradayDataRepository
) {
    operator fun invoke(
        ticker: String,
        interval: IntradayInterval
    ): Flow<Resource<IntradayData>> = flow {
        emit(Resource.Loading())

        try {
            val data = repository.getIntradayData(
                ticker,
                interval
            )

            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: ApiException) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "Api Error"))
        }
    }
}
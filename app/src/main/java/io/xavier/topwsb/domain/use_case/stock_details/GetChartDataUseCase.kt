package io.xavier.topwsb.domain.use_case.stock_details

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.common.getDateOneMonthBeforeToday
import io.xavier.topwsb.common.getTodayFormatted
import io.xavier.topwsb.domain.model.chart_data.ChartData
import io.xavier.topwsb.domain.repository.chart_data.ChartDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetChartDataUseCase @Inject constructor(
    private val repository: ChartDataRepository
) {
    operator fun invoke(
        ticker: String
    ): Flow<Resource<ChartData>> = flow {
        emit(Resource.Loading())

        // request chart data for the following date ranges
        val from = getDateOneMonthBeforeToday()
        val to = getTodayFormatted()

        try {
            val data = repository.getChartData(ticker, from, to)

            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = e.localizedMessage ?: "Http Error occurred"
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = e.localizedMessage ?: "IO error occurred"
            ))
        }
    }
}
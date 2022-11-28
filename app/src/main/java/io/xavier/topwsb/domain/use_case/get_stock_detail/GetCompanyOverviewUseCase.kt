package io.xavier.topwsb.domain.use_case.get_stock_detail

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.remote.dto.company_overview.toCompanyOverview
import io.xavier.topwsb.domain.model.CompanyOverview
import io.xavier.topwsb.domain.repository.StockDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCompanyOverviewUseCase @Inject constructor(
    private val repository: StockDataRepository
) {
    operator fun invoke(symbol: String): Flow<Resource<CompanyOverview>> = flow {
        try {
            emit(Resource.Loading())
            val stock = repository.getCompanyOverview(symbol).toCompanyOverview()
            emit(Resource.Success(stock))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
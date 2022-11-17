package io.xavier.topwsb.domain.use_case.get_stonks

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.remote.dto.toStonk
import io.xavier.topwsb.domain.model.Stonk
import io.xavier.topwsb.domain.repository.StonkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStonksUseCase @Inject constructor(
    private val repository: StonkRepository
) {
    operator fun invoke(): Flow<Resource<List<Stonk>>> = flow {
        try {
            emit(Resource.Loading())
            val stocks = repository.getStonks().map {
                it.toStonk()
            }
            emit(Resource.Success(stocks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
package io.xavier.topwsb.domain.use_case.get_stonk_detail

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.remote.dto.toStonkDetail
import io.xavier.topwsb.domain.model.StonkDetail
import io.xavier.topwsb.domain.repository.StonkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStonkDetailUseCase @Inject constructor(
    private val repository: StonkRepository
) {
    operator fun invoke(symbol: String): Flow<Resource<StonkDetail>> = flow {
        try {
            emit(Resource.Loading())
            val stonk = repository.getStonkDetail(symbol).toStonkDetail()
            emit(Resource.Success(stonk))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
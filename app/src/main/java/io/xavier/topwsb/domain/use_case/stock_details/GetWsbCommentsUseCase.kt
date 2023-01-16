package io.xavier.topwsb.domain.use_case.stock_details

import io.xavier.topwsb.common.MILlIS_24_HOURS
import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.domain.exceptions.ERROR_HTTP
import io.xavier.topwsb.domain.exceptions.ERROR_NO_NETWORK
import io.xavier.topwsb.domain.model.WsbComment
import io.xavier.topwsb.domain.repository.WsbCommentsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.Instant
import javax.inject.Inject

/**
 * Use case class that performs an API call get a list of comments on /r/wallstreetbets
 * returned as a list of [WsbComment] wrapped in a [Resource].
 */
class GetWsbCommentsUseCase @Inject constructor(
    private val repository: WsbCommentsRepository
) {
    /**
     * Invokes asynchronous call to fetch list of comments for the current day
     * on /r/wallstreetbets that contain [ticker] in the comment body
     *
     * @param ticker ticker of the stock to query
     * @return flow with a [Resource] object. If successful, the resource will wrap a list of
     *  [WsbComment]
     */
    operator fun invoke(
        ticker: String
    ): Flow<Resource<List<WsbComment>>> = flow {
        try {
            emit(Resource.Loading())

            val comments = repository.getComments(
                ticker, Instant.now().epochSecond - MILlIS_24_HOURS
            )
            emit(Resource.Success(comments))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ERROR_HTTP))
        } catch (e: IOException) {
            emit(Resource.Error(ERROR_NO_NETWORK))
        }
    }
}
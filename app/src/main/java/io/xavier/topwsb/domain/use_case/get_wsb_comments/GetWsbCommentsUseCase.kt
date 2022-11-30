package io.xavier.topwsb.domain.use_case.get_wsb_comments

import io.xavier.topwsb.common.Resource
import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentDto
import io.xavier.topwsb.domain.model.WsbComment
import io.xavier.topwsb.domain.repository.WsbCommentsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case class that performs an API call get a list of comments on /r/wallstreetbets
 * returned as a list of [WsbComment] wrapped in a [Resource].
 */
class GetWsbCommentsUseCase @Inject constructor(
    private val repository: WsbCommentsRepository
) {
    /**
     * Invokes asynchronous call to fetch list of comments created after [afterUtc]
     * on /r/wallstreetbets that contain [ticker] in the comment body
     *
     * @param ticker ticker of the stock to query
     * @param afterUtc only fetch comments created after this epoch time
     * @return flow with a [Resource] object. If successful, the resource will wrap a list of
     *  [WsbComment]
     */
    operator fun invoke(
        ticker: String,
        afterUtc: Int
    ): Flow<Resource<List<WsbCommentDto>>> = flow {
        try {
            emit(Resource.Loading())

            val comments = repository.getComments(ticker, afterUtc).getComments()
            emit(Resource.Success(comments))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
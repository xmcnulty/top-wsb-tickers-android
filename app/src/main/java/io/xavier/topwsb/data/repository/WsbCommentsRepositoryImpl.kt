package io.xavier.topwsb.data.repository

import io.xavier.topwsb.data.local.TrendiesDatabase
import io.xavier.topwsb.data.remote.WsbCommentsApi
import io.xavier.topwsb.data.repository.exceptions.APIException
import io.xavier.topwsb.domain.model.wsb_comment.WsbComment
import io.xavier.topwsb.domain.repository.WsbCommentsRepository
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WsbCommentsRepositoryImpl @Inject constructor(
    private val api: WsbCommentsApi,
    db: TrendiesDatabase
) : WsbCommentsRepository {

    private val dao = db.commentsDao

    override suspend fun getComments(
        ticker: String,
        afterUtc: Long
    ): List<WsbComment> {
        val comments = dao.getWsbComments(ticker)

        return comments.ifEmpty { refreshComments(ticker, afterUtc) }
    }

    override suspend fun refreshComments(ticker: String, afterUtc: Long): List<WsbComment> {
        val comments = try {
            api.getComments(ticker, afterUtc).data.map { dto ->
                WsbComment.buildFromDto(dto, ticker)
            }
        } catch(e: Exception) {
            when(e) {
                is IOException -> throw APIException.NoNetwork
                else -> throw APIException.NoComments
            }
        }

        dao.insertComments(comments)

        return comments
    }
}
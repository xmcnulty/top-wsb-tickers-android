package io.xavier.topwsb.data.repository

import io.xavier.topwsb.data.local.TrendiesDatabase
import io.xavier.topwsb.data.remote.WsbCommentsApi
import io.xavier.topwsb.domain.mapper.toWsbComment
import io.xavier.topwsb.domain.model.WsbComment
import io.xavier.topwsb.domain.repository.WsbCommentsRepository
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
        val comments = api.getComments(ticker, afterUtc).data.map { dto ->
            dto.toWsbComment(ticker)
        }

        dao.insertComments(comments)

        return dao.getWsbComments(ticker)
    }
}
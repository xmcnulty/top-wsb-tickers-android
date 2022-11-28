package io.xavier.topwsb.data.repository

import io.xavier.topwsb.data.remote.WsbCommentsApi
import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentsResponse
import io.xavier.topwsb.domain.repository.WsbCommentsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WsbCommentsRespositoryImpl @Inject constructor(
    private val api: WsbCommentsApi
) : WsbCommentsRepository {

    override suspend fun getComments(ticker: String, afterUtc: Int): WsbCommentsResponse {
        return api.getComments(ticker, afterUtc)
    }
}
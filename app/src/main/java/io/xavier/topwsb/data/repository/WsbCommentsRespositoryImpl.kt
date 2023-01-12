package io.xavier.topwsb.data.repository

import android.util.Log
import io.xavier.topwsb.data.remote.WsbCommentsApi
import io.xavier.topwsb.domain.mapper.toWsbComment
import io.xavier.topwsb.domain.model.WsbComment
import io.xavier.topwsb.domain.repository.WsbCommentsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WsbCommentsRespositoryImpl @Inject constructor(
    private val api: WsbCommentsApi
) : WsbCommentsRepository {

    override suspend fun getComments(ticker: String, afterUtc: Long): List<WsbComment> {
        Log.d("Comments Repository", "Requesting comments containing $ticker after $afterUtc")

        return api.getComments(ticker, afterUtc).getComments().map {
            it.toWsbComment()
        }
    }
}
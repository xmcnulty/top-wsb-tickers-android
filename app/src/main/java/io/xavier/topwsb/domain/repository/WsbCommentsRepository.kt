package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentsResponse


interface WsbCommentsRepository {
    suspend fun getComments(ticker: String, afterUtc: Int): WsbCommentsResponse
}
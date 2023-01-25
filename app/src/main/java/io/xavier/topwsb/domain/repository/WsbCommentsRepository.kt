package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.domain.model.wsb_comment.WsbComment


interface WsbCommentsRepository {
    suspend fun getComments(ticker: String, afterUtc: Long): List<WsbComment>

    suspend fun refreshComments(ticker: String, afterUtc: Long): List<WsbComment>
}
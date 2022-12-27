package io.xavier.topwsb.domain.repository

import io.xavier.topwsb.domain.model.WsbComment


interface WsbCommentsRepository {
    suspend fun getComments(ticker: String, afterUtc: Long): List<WsbComment>
}
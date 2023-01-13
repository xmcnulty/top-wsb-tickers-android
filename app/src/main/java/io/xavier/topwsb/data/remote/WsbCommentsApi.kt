package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for queries to pushshift.io API.
 */
interface WsbCommentsApi {

    /**
     * Gets comments from the wallstreetbets subreddit. Returns results sorted by created_utc
     * time in descending order.
     *
     * @param ticker query parameter to search for comments mentioning the specified stock ticker
     * @param afterUtc search comments after this epoch time
     * @param limit limits the number of comments returned. Default is 100.
     * @return list of [WsbCommentsResponse] objects parsed by GSON
     */
    @GET("reddit/comment/search?subreddit=wallstreetbets&sort_by=created_utc")
    suspend fun getComments(
        @Query("q") ticker: String,
        @Query("after") afterUtc: Long,
        @Query("limit") limit: Int = 100
    ): WsbCommentsResponse
}
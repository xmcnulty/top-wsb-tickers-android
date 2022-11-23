package io.xavier.topwsb.data.remote

import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentDto
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
     * @return list of [WsbCommentDto] objects parsed by GSON
     */
    @GET("reddit/comment/search?subreddit=wallstreetbets&sort_by=created_utc&sort=desc")
    suspend fun getComments(
        @Query("q") ticker: String,
        @Query("after") afterUtc: Int
    ): List<WsbCommentDto>
}
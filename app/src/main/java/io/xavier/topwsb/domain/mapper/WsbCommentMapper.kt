package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentDto
import io.xavier.topwsb.domain.model.WsbComment

/**
 * Maps a data transfer object (DTO) of a wsb comment to a [WsbComment] object.
 *
 * @param ticker the stock ticker that this comment is referencing.
 * @return [WsbComment] generated from this DTO
 */
fun WsbCommentDto.toWsbComment(ticker: String): WsbComment =
    WsbComment(
        text = this.body,
        ticker = ticker,
        createdUtc = this.created_utc * 1_000, // convert from seconds to ms
        author = this.author,
        permalink = this.permalink
    )
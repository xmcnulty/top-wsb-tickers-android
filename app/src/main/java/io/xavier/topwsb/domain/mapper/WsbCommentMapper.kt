package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentDto
import io.xavier.topwsb.domain.model.WsbComment

/**
 * Converts [WsbCommentDto] object to [WsbComment] to be used for this application.
 */
fun WsbCommentDto.toWsbComment(): WsbComment = WsbComment(
    text = body,
    createdUtc = created_utc,
    author = author,
    permalink = permalink
)
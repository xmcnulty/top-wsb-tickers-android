package io.xavier.topwsb.domain.mapper

import io.xavier.topwsb.data.remote.dto.wsb_comments.WsbCommentDto
import io.xavier.topwsb.domain.model.WsbComment

fun WsbCommentDto.toWsbComment(): WsbComment =
    WsbComment(
        text = this.body,
        createdUtc = this.created_utc * 1_000, // convert from seconds to ms
        author = this.author,
        permalink = this.permalink
    )
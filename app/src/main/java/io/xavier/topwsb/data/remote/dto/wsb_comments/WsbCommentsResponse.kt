package io.xavier.topwsb.data.remote.dto.wsb_comments

import io.xavier.topwsb.domain.mapper.toWsbComment
import io.xavier.topwsb.domain.model.WsbComment

data class WsbCommentsResponse(
    val data: List<WsbCommentDto>
) {
    fun getComments(): List<WsbComment> = data.map {
        it.toWsbComment()
    }
}
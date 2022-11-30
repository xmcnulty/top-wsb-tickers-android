package io.xavier.topwsb.data.remote.dto.wsb_comments

data class WsbCommentsResponse(
    val data: List<WsbCommentDto>
) {
    fun getComments(): List<WsbCommentDto> = data
}
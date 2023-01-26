package io.xavier.topwsb.presentation.stock_detail.components.comments

import io.xavier.topwsb.domain.model.wsb_comment.WsbComment

/**
 * Sealed class representing the possible states of the comment section for the stock detail screen.
 * @property Loading
 * @property Error contains an error message string
 * @property Success contains a list of [WsbComment] objects
 */
sealed interface CommentsState {
    object Loading : CommentsState
    data class Error(val errorMsgResId: Int?) : CommentsState
    data class Success(val comments: List<WsbComment>) : CommentsState
}
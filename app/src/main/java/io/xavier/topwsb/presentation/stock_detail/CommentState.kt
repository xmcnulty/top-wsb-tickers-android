package io.xavier.topwsb.presentation.stock_detail

import io.xavier.topwsb.domain.model.WsbComment

/**
 * Contains the state of the comment section of stock detail screen
 *
 * @property loading true if loading data from api
 * @property errorMsg error message to display in comment area
 * @property comments list of comments returned from the api
 */
data class CommentState(
    var loading: Boolean = false,
    var errorMsg: String = "",
    var comments: List<WsbComment>? = null
)

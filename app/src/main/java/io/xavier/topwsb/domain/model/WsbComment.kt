package io.xavier.topwsb.domain.model

data class WsbComment(
    val text: String,
    val createdUtc: Long,
    val author: String,
    val permalink: String
)
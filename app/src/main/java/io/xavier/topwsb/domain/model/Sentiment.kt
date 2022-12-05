package io.xavier.topwsb.domain.model

import androidx.compose.ui.graphics.Color
import io.xavier.topwsb.presentation.theme.DarkSelectedCard
import io.xavier.topwsb.presentation.theme.NegativeTrend
import io.xavier.topwsb.presentation.theme.PositiveTrend

/**
 * Enumerated sentiments for stock.
 *
 * @property text string representation for this
 * @property color color representation for this
 */
enum class Sentiment(val text: String, val color: Color) {
    BULLISH("Bullish", PositiveTrend),
    BEARISH("Bearish", NegativeTrend),
    UNKNOWN("Unknown", DarkSelectedCard); // when sentiment is unknown.

    companion object {
        fun fromName(sentiment: String): Sentiment = when {
            sentiment.equals(BULLISH.text, true) -> BULLISH
            sentiment.equals(BEARISH.text, true) -> BEARISH
            else -> UNKNOWN
        }
    }
}
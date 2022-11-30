package io.xavier.topwsb.common

object Constants {

    const val TRADESTIE_API_BASE_URL = "https://tradestie.com/api/"
    const val ALPHA_ADVANTAGE_API_BASE_URL = "https://www.alphavantage.co/"
    const val PUSH_SHIFT_BASE_URL = "https://api.pushshift.io/"

    const val PARAM_STOCK_SYMBOL = "ticker"
}

// Database constants
const val TABLE_NAME_TRENDING_STOCKS = "trending_stocks"

const val TICKER_COL_NAME = "ticker"
const val LAST_UPDATED_COL_NAME = "last_updated_utc"
const val NUMBER_OF_COMMENTS_COL_NAME = "number_of_comments"
const val SENTIMENT_COL_NAME = "sentiment"
const val SENTIMENT_SCORE_COL_NAME = "sentiment_score"
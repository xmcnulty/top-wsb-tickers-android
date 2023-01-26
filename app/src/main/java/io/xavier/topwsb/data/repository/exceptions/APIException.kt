package io.xavier.topwsb.data.repository.exceptions

import io.xavier.topwsb.R

/**
 * Exception thrown when there is an error making a remote API call. Sealed class contains
 * objects for different errors that may occur when making remote API calls. Each error contains a
 * an id to a string resource so a message can be displayed to the user.
 *
 * @param errorResId string resource id for message to be displayed to user.
 * @author xmcnulty
 */
sealed class APIException(val errorResId: Int) : Exception() {
    object NoNetwork : APIException(R.string.error_no_network)
    object NoTrendingStocks : APIException(R.string.error_no_trending_stocks)
    object Unexpected : APIException(R.string.error_unexpected)
    object NoChartData : APIException(R.string.error_no_chart_data)
}
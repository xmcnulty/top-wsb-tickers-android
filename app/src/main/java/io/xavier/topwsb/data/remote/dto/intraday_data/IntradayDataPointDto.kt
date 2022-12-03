package io.xavier.topwsb.data.remote.dto.intraday_data

import com.google.gson.annotations.SerializedName

/**
 * Time series data point as returned from alpha advantage API.
 *
 * @property open
 * @property high
 * @property low
 * @property close
 * @property volume
 */
data class IntradayDataPointDto(
    @SerializedName("1. open")
    val open: String,
    @SerializedName("2. high")
    val high: String,
    @SerializedName("3. low")
    val low: String,
    @SerializedName("4. close")
    val close: String,
    @SerializedName("5. volume")
    val volume: String
)

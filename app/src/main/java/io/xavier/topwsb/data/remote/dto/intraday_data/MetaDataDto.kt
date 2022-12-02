package io.xavier.topwsb.data.remote.dto.intraday_data

import com.google.gson.annotations.SerializedName

data class MetaDataDto(
    @SerializedName("1. Information")
    val information: String,
    @SerializedName("2. Symbol")
    val symbol: String,
    @SerializedName("3. Last Refreshed")
    val lastRefreshed: String,
    @SerializedName("4. Interval")
    val interval: String,
    @SerializedName("5. Output Size")
    val outputSize: String,
    @SerializedName("6. Time Zone")
    val timeZone: String
)
package io.xavier.topwsb.data.remote.dto.intraday_data

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object for data returned from alpha advantage api.
 *
 * @property metaData [MetaDataDto]
 * @property timeSeries map of string (date and time) to [IntradayDataPointDto]
 */
data class IntradayDataDto(
    @SerializedName("Meta Data")
    val metaData: MetaDataDto,
    @SerializedName("Time Series (30min)")
    val timeSeries: Map<String, IntradayDataPointDto>
)
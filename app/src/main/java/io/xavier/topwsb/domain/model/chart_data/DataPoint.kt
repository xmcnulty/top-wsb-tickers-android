package io.xavier.topwsb.domain.model.chart_data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPoint(
    val time: Long,
    val close: Double,
    val high: Double,
    val low: Double,
    val open: Double
) : Parcelable

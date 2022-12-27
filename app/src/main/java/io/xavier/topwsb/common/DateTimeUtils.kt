package io.xavier.topwsb.common

import android.util.Log
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

/**
 * Gets the time in milliseconds for midnight (start) of the current day.
 *
 * @return start time of the day in milliseconds
 */
fun getTodayMidnightMillis(): Long {
    val now = LocalDateTime.now()

    val midnight = LocalDateTime.of(
        now.year,
        now.month.value,
        now.dayOfMonth,
        0,
        0
    )

    // TODO: Remove logging for production.
    val midnightMillis = midnight.toEpochSecond(ZoneOffset.of(ZoneId.systemDefault().id))
    Log.d("Date Time Utils", "Midnight milliseconds: $midnightMillis")
    return midnightMillis
}
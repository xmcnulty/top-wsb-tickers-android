package io.xavier.topwsb.common

import android.util.Log
import java.time.LocalDate
import java.time.ZoneId

/**
 * Gets the time in milliseconds for midnight (start) of the current day.
 *
 * @return start time of the day in milliseconds
 */
fun startOfDayMilliseconds(): Long {
    val now = LocalDate.now()

    val dayStart = now.atStartOfDay(ZoneId.systemDefault())

    // TODO: Remove logging for production.
    val midnightMillis = dayStart.toEpochSecond()
    Log.d("Date Time Utils", "Midnight milliseconds: $midnightMillis")
    return midnightMillis
}
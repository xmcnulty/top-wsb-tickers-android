package io.xavier.topwsb.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

// number of milliseconds in 24 hours
const val MILlIS_24_HOURS: Long = 60 * 60 * 24

@SuppressLint("SimpleDateFormat")
fun getDateOneMonthBeforeToday(): String {
    val cal = Calendar.getInstance()
    cal.add(Calendar.MONTH, -1)
    val dateFormat = SimpleDateFormat("yyyy-MM-DD")
    return dateFormat.format(cal.time)
}
@SuppressLint("SimpleDateFormat")
fun getTodayFormatted(): String {
    val cal = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-DD")
    return dateFormat.format(cal.time)
}
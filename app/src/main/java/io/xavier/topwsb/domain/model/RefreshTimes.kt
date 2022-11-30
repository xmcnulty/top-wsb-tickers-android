package io.xavier.topwsb.domain.model

/**
 * Enumeration of available time intervals to refresh data.
 * Minimum is 15 minutes as this is how often the tradestie api refreshes data.
 *
 * @property milliseconds number of milliseconds in each interval
 */
enum class RefreshTimes(val milliseconds: Long) {
    ONE_HOUR(3_600_000),
    FORTY_FIVE_MINUTES(2_700_000),
    THIRTY_MINUTES(1_800_000),
    FIFTEEN_MINUTES(900_000)
}
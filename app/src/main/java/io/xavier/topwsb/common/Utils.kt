package io.xavier.topwsb.common

import io.xavier.topwsb.domain.model.RefreshTimes
import io.xavier.topwsb.domain.model.TrendingStock

/**
 * Is the current data outdated?
 *
 * @param interval refresh time interval
 * @return true if current time - [TrendingStock.lastUpdatedUtc] is past [interval]
 */
fun TrendingStock.isOutdated(interval: RefreshTimes) =
    System.currentTimeMillis() - this.lastUpdatedUtc > interval.milliseconds
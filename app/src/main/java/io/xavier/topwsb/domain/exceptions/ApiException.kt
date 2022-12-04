package io.xavier.topwsb.domain.exceptions

/**
 * Exception thrown when an API returns an empty result, but still has a status HTTP status code of
 * 200. This was observed to be happening with the Alpha Advantage API when requesting intraday
 * time-series data for some tickers.
 * For more details see https://github.com/xmcnulty/wsb-trendies/issues/22
 *
 * @author xmcnulty
 */
class ApiException(message: String) : Exception(message)
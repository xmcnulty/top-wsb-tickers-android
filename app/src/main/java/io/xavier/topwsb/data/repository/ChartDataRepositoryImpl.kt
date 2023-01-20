package io.xavier.topwsb.data.repository

import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.data.remote.PolygonApi
import io.xavier.topwsb.domain.model.chart_data.ChartData
import io.xavier.topwsb.domain.repository.chart_data.ChartDataRepository
import io.xavier.topwsb.domain.repository.chart_data.TimeSpan
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChartDataRepositoryImpl @Inject constructor(
    private val api: PolygonApi
) : ChartDataRepository {
    override suspend fun getChartData(
        ticker: String,
        from: String,
        to: String
    ): ChartData {
        val result = api.getChartData(
            ticker = ticker,
            multiplier = 1,
            timespan = TimeSpan.HOUR,
            from = from,
            to = to,
            apiKey = BuildConfig.API_KEY_POLYGON
        )

        return ChartData.build(result)
    }
}
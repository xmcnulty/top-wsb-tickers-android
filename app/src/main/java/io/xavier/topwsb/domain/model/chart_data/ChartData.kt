package io.xavier.topwsb.domain.model.chart_data

import android.os.Parcelable
import io.xavier.topwsb.data.remote.dto.chart_data.ChartDataDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChartData(
    val ticker: String,
    val dataPoints: List<DataPoint>
) : Parcelable {
    companion object {
        /**
         * Builds chart data from a chart data data transfer object (DTO)
         *
         * @param dto [ChartDataDto]
         * @return [ChartData] built from [dto]
         */
        fun build(dto: ChartDataDto): ChartData {
            return ChartData(
                ticker = dto.ticker,
                dataPoints = dto.results.map { result ->
                    DataPoint(
                        time = result.t,
                        close = result.c,
                        high = result.h,
                        low = result.l,
                        open = result.o
                    )
                }
            )
        }
    }
}

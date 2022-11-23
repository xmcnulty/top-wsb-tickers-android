package io.xavier.topwsb.domain.model

/**
 * Data point for drawing line graph.
 */
data class DataPoint(
    val y: Double,
    val xLabel: String?,
    val yLabel: String?
)

package io.xavier.topwsb.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import io.xavier.topwsb.common.*
import io.xavier.topwsb.data.local.entities.TrendingStockEntity

/**
 * Data class and database table that stores basic information about a stock. The information
 * contained is:
 *
 * @property ticker exchange ticker of the stock
 * @property companyName company name of the stock
 * @property high52Week highest price of the stock in the past 52 weeks
 * @property low52Week lowest price of the stock in the past 52 weeks
 * @property analystTargetPrice target price by financial analysts for this stock
 */
@Entity(
    tableName = TABLE_NAME_STOCK_OVERVIEW,
    foreignKeys = [ForeignKey(
        entity = TrendingStockEntity::class,
        parentColumns = arrayOf(TICKER_STOCK_COL_NAME),
        childColumns = arrayOf(TICKER_OVERVIEW_COL_NAME),
        onUpdate = CASCADE,
        onDelete = CASCADE
    )]
)
data class MarketData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = TICKER_OVERVIEW_COL_NAME)
    val ticker: String,

    @ColumnInfo(name = COMPANY_NAME_COL_NAME)
    val companyName: String,

    @ColumnInfo(name = HIGH_52WEEK_COL_NAME)
    val high52Week: String,

    @ColumnInfo(name = LOW_52WEEK_COL_NAME)
    val low52Week: String,

    @ColumnInfo(name = ANALYST_TGT_COL_NAME)
    val analystTargetPrice: String
)


package io.xavier.topwsb.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.xavier.topwsb.common.Constants
import io.xavier.topwsb.data.local.TrendiesDatabase
import io.xavier.topwsb.data.remote.PolygonApi
import io.xavier.topwsb.data.remote.TrendingStockApi
import io.xavier.topwsb.data.remote.WsbCommentsApi
import io.xavier.topwsb.data.repository.ChartDataRepositoryImpl
import io.xavier.topwsb.data.repository.TrendingStockRepositoryImpl
import io.xavier.topwsb.data.repository.WsbCommentsRepositoryImpl
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import io.xavier.topwsb.domain.repository.WsbCommentsRepository
import io.xavier.topwsb.domain.repository.chart_data.ChartDataRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPolygonApi(): PolygonApi {
        return Retrofit.Builder()
            .baseUrl(Constants.POLYGON_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PolygonApi::class.java)
    }

    /**
     * Provides a singleton instance of [TrendingStockApi] that fetches trending stocks
     * on /r/wallstreetbets.
     *
     * @return [TrendingStockApi]
     */
    @Provides
    @Singleton
    fun providesTrendingStockApi(): TrendingStockApi {
        return Retrofit.Builder()
            .baseUrl(Constants.TRADESTIE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrendingStockApi::class.java)
    }

    /**
     * Provides a singleton instance of [WsbCommentsApi] that fetches a list of comments
     * from /r/wallstreetbets that mention a queried stock ticker.
     *
     * @return [WsbCommentsApi]
     */
    @Provides
    @Singleton
    fun providesWsbCommentApi(): WsbCommentsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.PUSH_SHIFT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WsbCommentsApi::class.java)
    }

    /**
     * Provides a singleton instance of [TrendingStockRepository]
     */
    @Provides
    @Singleton
    fun providesTrendingStockRepository(
        wsbApi: TrendingStockApi,
        polygonApi: PolygonApi,
        db: TrendiesDatabase
    ): TrendingStockRepository {
        return TrendingStockRepositoryImpl(wsbApi, polygonApi, db)
    }

    @Provides
    @Singleton
    fun providesWsbCommentsRepository(
        wsbCommentsApi: WsbCommentsApi,
        db: TrendiesDatabase
    ): WsbCommentsRepository {
        return WsbCommentsRepositoryImpl(wsbCommentsApi, db)
    }

    @Provides
    @Singleton
    fun providesChartDataRepository(
        api: PolygonApi
    ): ChartDataRepository {
        return ChartDataRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesTrendingStockDB(app: Application): TrendiesDatabase {
        return Room.databaseBuilder(
            app,
            TrendiesDatabase::class.java,
            "trending_stock.db"
        ).build()
    }
}
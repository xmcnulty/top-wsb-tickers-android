package io.xavier.topwsb.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.xavier.topwsb.BuildConfig
import io.xavier.topwsb.common.Constants
import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.remote.StockDataApi
import io.xavier.topwsb.data.remote.TrendingStockApi
import io.xavier.topwsb.data.remote.WsbCommentsApi
import io.xavier.topwsb.data.repository.IntradayDataRepositoryImpl
import io.xavier.topwsb.data.repository.StockOverviewRepositoryImpl
import io.xavier.topwsb.data.repository.TrendingStockRepositoryImpl
import io.xavier.topwsb.data.repository.WsbCommentsRespositoryImpl
import io.xavier.topwsb.domain.repository.IntradayDataRepository
import io.xavier.topwsb.domain.repository.StockOverviewRepository
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import io.xavier.topwsb.domain.repository.WsbCommentsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
     * Provides a singleton instance of [StockDataApi] that fetches company overview from
     * Alpha Advantage.
     *
     * @return [StockDataApi]
     */
    @Provides
    @Singleton
    fun providesStockDataApi(): StockDataApi {

        // Log OkHttp Traffic if debug mode.
        val client = if (BuildConfig.DEBUG) {
            BuildConfig.DEBUG
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC

            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        } else {
            OkHttpClient.Builder().build()
        }

        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.ALPHA_ADVANTAGE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockDataApi::class.java)
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
        db: TrendingStockDatabase
    ): TrendingStockRepository {
        return TrendingStockRepositoryImpl(wsbApi, db)
    }

    @Provides
    @Singleton
    fun providesStockDataRepository(
        stockDataApi: StockDataApi,
        db: TrendingStockDatabase
    ): StockOverviewRepository {
        return StockOverviewRepositoryImpl(stockDataApi, db)
    }

    @Provides
    @Singleton
    fun providesWsbCommentsRepository(
        wsbCommentsApi: WsbCommentsApi
    ): WsbCommentsRepository {
        return WsbCommentsRespositoryImpl(wsbCommentsApi)
    }

    @Provides
    @Singleton
    fun providesIntradayRepository(
        stockDataApi: StockDataApi
    ): IntradayDataRepository {
        return IntradayDataRepositoryImpl(stockDataApi)
    }

    @Provides
    @Singleton
    fun providesTrendingStockDB(app: Application): TrendingStockDatabase {
        return Room.databaseBuilder(
            app,
            TrendingStockDatabase::class.java,
            "trending_stock.db"
        ).build()
    }
}
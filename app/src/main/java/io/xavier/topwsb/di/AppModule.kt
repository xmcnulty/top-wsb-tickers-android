package io.xavier.topwsb.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.xavier.topwsb.common.Constants
import io.xavier.topwsb.data.local.TrendingStockDatabase
import io.xavier.topwsb.data.remote.StockDataApi
import io.xavier.topwsb.data.remote.TrendingStockApi
import io.xavier.topwsb.data.repository.StockDataRepositoryImpl
import io.xavier.topwsb.data.repository.TrendingStockRepositoryImpl
import io.xavier.topwsb.domain.repository.StockDataRepository
import io.xavier.topwsb.domain.repository.TrendingStockRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTrendingStockApi(): TrendingStockApi {
        return Retrofit.Builder()
            .baseUrl(Constants.TRADESTIE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrendingStockApi::class.java)
    }

    @Provides
    @Singleton
    fun providesStockDataApi(): StockDataApi {
        return Retrofit.Builder()
            .baseUrl(Constants.ALPHA_ADVANTAGE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockDataApi::class.java)
    }

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
        stockDataApi: StockDataApi
    ): StockDataRepository {
        return StockDataRepositoryImpl(stockDataApi)
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
package io.xavier.topwsb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.xavier.topwsb.common.Constants
import io.xavier.topwsb.data.remote.AlphaAdvantageApi
import io.xavier.topwsb.data.remote.TradestieRedditApi
import io.xavier.topwsb.data.repository.StockRepositoryImpl
import io.xavier.topwsb.domain.repository.StockRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWsbApi(): TradestieRedditApi {
        return Retrofit.Builder()
            .baseUrl(Constants.WSB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TradestieRedditApi::class.java)
    }

    @Provides
    @Singleton
    fun providesAlphaAdvApi(): AlphaAdvantageApi {
        return Retrofit.Builder()
            .baseUrl(Constants.ALPHA_ADV_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlphaAdvantageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStockRepository(
        wsbApi: TradestieRedditApi,
        alphaAdvApi: AlphaAdvantageApi
    ): StockRepository {
        return StockRepositoryImpl(wsbApi, alphaAdvApi)
    }
}
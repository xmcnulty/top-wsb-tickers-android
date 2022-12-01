package io.xavier.topwsb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.DateFormat
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Annotation for [TimeOnlyFormatter]
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TimeOnlyFormatter

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    /**
     * Provides a [DateFormat] used to format time to h:mm <am/pm>
     */
    @Provides
    @Singleton
    @TimeOnlyFormatter
    fun provideTimeFormatter(): DateFormat {
        return DateFormat.getTimeInstance(DateFormat.SHORT)
    }
}
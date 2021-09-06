package com.shivamkumarjha.airlinescompose.di

import android.content.Context
import androidx.room.Room
import com.shivamkumarjha.airlinescompose.config.Constants
import com.shivamkumarjha.airlinescompose.persistence.AirlineDao
import com.shivamkumarjha.airlinescompose.persistence.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun airlineDao(appDatabase: AppDatabase): AirlineDao = appDatabase.airlineDao()

}
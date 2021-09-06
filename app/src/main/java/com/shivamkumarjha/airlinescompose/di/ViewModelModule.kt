package com.shivamkumarjha.airlinescompose.di

import com.shivamkumarjha.airlinescompose.network.ApiService
import com.shivamkumarjha.airlinescompose.persistence.AirlineDao
import com.shivamkumarjha.airlinescompose.repository.AirlineRepository
import com.shivamkumarjha.airlinescompose.repository.AirlineRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun getAirlineRepository(
        apiService: ApiService,
        airlineDao: AirlineDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AirlineRepository {
        return AirlineRepositoryImpl(apiService, airlineDao, ioDispatcher)
    }

}
package com.shivamkumarjha.airlinescompose.di

import com.google.gson.Gson
import com.shivamkumarjha.airlinescompose.config.Constants
import com.shivamkumarjha.airlinescompose.network.ApiService
import com.shivamkumarjha.airlinescompose.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun getApiService(okHttpClient: OkHttpClient, gson: Gson): ApiService =
        RetrofitClient.getClient(Constants.BASE_URL, okHttpClient, gson)
            .create(ApiService::class.java)
}
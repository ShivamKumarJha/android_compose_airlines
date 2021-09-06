package com.shivamkumarjha.airlinescompose.network

import com.shivamkumarjha.airlinescompose.config.Constants
import com.shivamkumarjha.airlinescompose.model.AirlinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("passenger")
    suspend fun getPassengers(
        @Query("page") page: Int,
        @Query("size") size: Int = Constants.PAGE_SIZE,
    ): Response<AirlinesResponse>

}
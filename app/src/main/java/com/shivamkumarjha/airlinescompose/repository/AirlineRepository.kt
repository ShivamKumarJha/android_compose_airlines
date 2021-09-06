package com.shivamkumarjha.airlinescompose.repository

import com.shivamkumarjha.airlinescompose.model.AirlinesResponse
import com.shivamkumarjha.airlinescompose.network.Resource
import kotlinx.coroutines.flow.Flow

interface AirlineRepository {
    suspend fun getPassengers(page: Int): Flow<Resource<AirlinesResponse?>>
}
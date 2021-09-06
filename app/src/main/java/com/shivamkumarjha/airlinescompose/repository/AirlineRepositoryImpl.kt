package com.shivamkumarjha.airlinescompose.repository

import android.util.Log
import com.shivamkumarjha.airlinescompose.config.Constants
import com.shivamkumarjha.airlinescompose.di.IoDispatcher
import com.shivamkumarjha.airlinescompose.model.AirlinesResponse
import com.shivamkumarjha.airlinescompose.model.ParsedAirline
import com.shivamkumarjha.airlinescompose.network.ApiService
import com.shivamkumarjha.airlinescompose.network.NoConnectivityException
import com.shivamkumarjha.airlinescompose.network.Resource
import com.shivamkumarjha.airlinescompose.persistence.AirlineDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AirlineRepositoryImpl(
    private val apiService: ApiService,
    private val airlineDao: AirlineDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AirlineRepository {

    override suspend fun getPassengers(page: Int): Flow<Resource<AirlinesResponse?>> = flow {
        emit(Resource.loading(data = null))
        try {
            val response = apiService.getPassengers(page)
            if (response.isSuccessful) {
                val data = response.body()
                emit(Resource.success(data = data))
                Log.d(Constants.TAG, data.toString())
                //Save to database
                if (page == 0) {
                    airlineDao.clearAirlines()
                }
                data?.data?.forEach {
                    val parsedAirline = ParsedAirline(
                        it.id,
                        it.name,
                        it.trips,
                        it.airline.first().name,
                        it.airline.first().country,
                        it.airline.first().logo,
                        it.airline.first().slogan,
                        it.airline.first().headQuarters,
                        it.airline.first().website,
                        it.airline.first().established,
                        page
                    )
                    airlineDao.addAirline(parsedAirline)
                }
            } else {
                emit(Resource.error(data = null, message = response.code().toString()))
                Log.d(Constants.TAG, response.code().toString())
            }
        } catch (exception: Exception) {
            if (exception is NoConnectivityException)
                emit(Resource.offline(data = null))
            else {
                emit(Resource.error(data = null, message = exception.message.toString()))
                Log.e(Constants.TAG, exception.message.toString())
            }
        }
    }.flowOn(ioDispatcher)

}
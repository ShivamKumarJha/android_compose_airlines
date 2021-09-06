package com.shivamkumarjha.airlinescompose.ui.main

import androidx.lifecycle.*
import com.shivamkumarjha.airlinescompose.di.IoDispatcher
import com.shivamkumarjha.airlinescompose.model.AirlinesResponse
import com.shivamkumarjha.airlinescompose.network.Resource
import com.shivamkumarjha.airlinescompose.persistence.AirlineDao
import com.shivamkumarjha.airlinescompose.repository.AirlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val airlineDao: AirlineDao,
    private val airlineRepository: AirlineRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _airlinesResponse = MutableLiveData<Resource<AirlinesResponse?>>()
    val airlinesResponse: LiveData<Resource<AirlinesResponse?>> = _airlinesResponse
    val airlinesDao = liveData(ioDispatcher) {
        emitSource(airlineDao.getAirlines())
    }

    fun getPassengers(page: Int) {
        viewModelScope.launch(ioDispatcher) {
            airlineRepository.getPassengers(page).collect {
                _airlinesResponse.postValue(it)
            }
        }
    }

}
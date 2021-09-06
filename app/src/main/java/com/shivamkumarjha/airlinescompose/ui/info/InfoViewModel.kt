package com.shivamkumarjha.airlinescompose.ui.info

import androidx.lifecycle.ViewModel
import com.shivamkumarjha.airlinescompose.persistence.AirlineDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val airlineDao: AirlineDao
) : ViewModel() {

    fun getAirline(id: String) = airlineDao.getAirline(id)
}
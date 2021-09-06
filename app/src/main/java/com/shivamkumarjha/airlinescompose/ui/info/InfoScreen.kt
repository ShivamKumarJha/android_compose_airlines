package com.shivamkumarjha.airlinescompose.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.shivamkumarjha.airlinescompose.ui.components.AppTopAppBar

@Composable
fun InfoScreen(
    id: String,
    viewModel: InfoViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val airline = viewModel.getAirline(id).collectAsState(null)

    Scaffold(
        topBar = {
            airline.value?.let {
                AppTopAppBar(title = it.airlineName) {
                    onBack()
                }
            }
        }
    ) {
        airline.value?.let {
            Column {
                Text(text = it.name)
                Text(text = if (it.trips == null) "0 trips" else "${it.trips} trips")
                Text(text = it.airlineName)
                Text(text = it.country)
                Text(text = it.slogan)
                Text(text = it.headQuarters)
                Text(text = it.website)
                Text(text = it.established.toString())
            }
        }
    }
}
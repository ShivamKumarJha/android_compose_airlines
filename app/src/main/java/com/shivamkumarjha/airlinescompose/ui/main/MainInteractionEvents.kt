package com.shivamkumarjha.airlinescompose.ui.main

import androidx.navigation.NavHostController
import com.shivamkumarjha.airlinescompose.config.Constants

sealed class MainInteractionEvents {
    data class OpenInfo(val id: String) : MainInteractionEvents()
}

fun handleInteractionEvents(
    interactionEvents: MainInteractionEvents,
    navController: NavHostController
) {
    when (interactionEvents) {
        is MainInteractionEvents.OpenInfo -> navController.navigate(
            "${Constants.NAV_INFO}/${interactionEvents.id}"
        )
    }
}
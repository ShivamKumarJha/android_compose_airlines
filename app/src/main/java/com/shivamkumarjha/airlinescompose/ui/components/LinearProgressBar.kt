package com.shivamkumarjha.airlinescompose.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LinearProgressBar(isVisible: MutableState<Boolean>, modifier: Modifier = Modifier) {
    Box(modifier) {
        AnimatedVisibility(visible = isVisible.value) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}
package com.shivamkumarjha.airlinescompose.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.shivamkumarjha.airlinescompose.network.Resource
import com.shivamkumarjha.airlinescompose.network.Status
import com.shivamkumarjha.airlinescompose.ui.components.JumpToPosition
import com.shivamkumarjha.airlinescompose.ui.components.LinearProgressBar
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel, interactionEvents: (MainInteractionEvents) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val airlines = viewModel.airlinesDao.observeAsState(arrayListOf())
    val airlinesResponse = viewModel.airlinesResponse.observeAsState(Resource.loading(null))
    val lastIndex = airlines.value.lastIndex
    var refreshing by remember { mutableStateOf(false) }
    val showBottomProgress = remember { mutableStateOf(false) }
    showBottomProgress.value = airlinesResponse.value.status == Status.LOADING && !refreshing

    // Show the jump button
    val jumpVisibility by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0 && !listState.isScrollInProgress
        }
    }

    Scaffold(
        bottomBar = {
            LinearProgressBar(showBottomProgress, modifier = Modifier.fillMaxWidth())
        }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            SwipeRefresh(
                state = rememberSwipeRefreshState(refreshing),
                onRefresh = {
                    refreshing = true
                    viewModel.getPassengers(0)
                },
            ) {
                LazyColumn(state = listState) {
                    itemsIndexed(airlines.value) { index, airline ->
                        AirlineItem(
                            airline,
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                .fillMaxWidth()
                                .clickable {
                                    interactionEvents(MainInteractionEvents.OpenInfo(airline.id))
                                }
                        )
                        //Paging
                        if (lastIndex == index) {
                            LaunchedEffect(Unit) {
                                viewModel.getPassengers(airline.page?.plus(1) ?: 0)
                            }
                        }
                        //Refresh
                        if (refreshing)
                            refreshing = false
                    }
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }

            JumpToPosition(
                enabled = jumpVisibility,
                scrollUp = true,
                onClicked = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )

        }

    }
}
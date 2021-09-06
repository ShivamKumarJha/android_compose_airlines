package com.shivamkumarjha.airlinescompose.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.shivamkumarjha.airlinescompose.config.Constants
import com.shivamkumarjha.airlinescompose.ui.info.InfoScreen

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MainNavigation() {
    val viewModel: MainViewModel = viewModel()

    val navController = rememberAnimatedNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    Scaffold { innerPadding ->
        ModalBottomSheetLayout(bottomSheetNavigator, modifier = Modifier.padding(innerPadding)) {
            AnimatedNavHost(
                navController,
                startDestination = Constants.NAV_MAIN,
            ) {
                composable(
                    Constants.NAV_MAIN,
                    enterTransition = { _, _ ->
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
                    },
                    exitTransition = { _, _ ->
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
                    },
                    popEnterTransition = { _, _ ->
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
                    },
                    popExitTransition = { _, _ ->
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
                    }
                ) {
                    MainScreen(viewModel) {
                        handleInteractionEvents(it, navController)
                    }
                }
                composable(
                    "${Constants.NAV_INFO}/{${Constants.ARG_ID}}",
                    arguments = listOf(navArgument(Constants.ARG_ID) {
                        type = NavType.StringType
                    }),
                    enterTransition = { _, _ ->
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
                    },
                    exitTransition = { _, _ ->
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
                    },
                    popEnterTransition = { _, _ ->
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
                    },
                    popExitTransition = { _, _ ->
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
                    }
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString(Constants.ARG_ID)
                    if (id != null) {
                        InfoScreen(id) {
                            navController.navigateUp()
                        }
                    }
                }
            }
        }
    }
}
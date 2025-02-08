package com.gondroid.randomchallengeapp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gondroid.randomchallengeapp.presentation.screens.detail.TaskScreenRoot
import com.gondroid.randomchallengeapp.presentation.screens.detail.TaskViewModel
import com.gondroid.randomchallengeapp.presentation.screens.home.HomeScreenRoot
import com.gondroid.randomchallengeapp.presentation.screens.home.HomeScreenViewModel

@Composable
fun NavigationRoot(
    navController: NavHostController
) {

    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        NavHost(
            navController = navController,
            startDestination = HomeScreenRoute
        ) {
            composable<HomeScreenRoute> {
                val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                HomeScreenRoot(
                    viewModel = homeScreenViewModel,
                    navigateToTaskScreen = { taskId ->
                        navController.navigate(
                            TaskScreenRoute(
                                taskId = taskId
                            )
                        )
                    }
                )
            }

            composable<TaskScreenRoute> {
                val taskViewModel = hiltViewModel<TaskViewModel>()
                TaskScreenRoot(
                    viewModel = taskViewModel,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

package com.gondroid.noteai.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gondroid.noteai.presentation.screens.TestScreenRoot
import com.gondroid.noteai.presentation.screens.detail.TaskScreenRoot
import com.gondroid.noteai.presentation.screens.detail.TaskViewModel
import com.gondroid.noteai.presentation.screens.home.HomeScreenRoot
import com.gondroid.noteai.presentation.screens.home.HomeScreenViewModel
import com.gondroid.noteai.presentation.screens.notes.NotesScreenRoot
import com.gondroid.noteai.presentation.screens.notes.NotesViewModel

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
            startDestination = TestScreenRoute
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

            composable<TestScreenRoute> {
                TestScreenRoot()
            }

            composable<NotesScreenRoute> {
                val notesViewModel = hiltViewModel<NotesViewModel>()
                NotesScreenRoot(
                    viewModel = notesViewModel
                )
            }
        }
    }
}

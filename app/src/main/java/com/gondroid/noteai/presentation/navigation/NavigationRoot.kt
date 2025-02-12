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
import com.gondroid.noteai.presentation.screens.noteCreate.NoteCreateScreenRoot
import com.gondroid.noteai.presentation.screens.noteCreate.NoteCreateViewModel
import com.gondroid.noteai.presentation.screens.notes.NoteScreenViewModel
import com.gondroid.noteai.presentation.screens.notes.NotesScreenRoot
import com.gondroid.noteai.presentation.screens.task.TaskScreenRoot
import com.gondroid.noteai.presentation.screens.task.TaskScreenViewModel
import com.gondroid.noteai.presentation.screens.taskCreate.TaskCreateScreenRoot
import com.gondroid.noteai.presentation.screens.taskCreate.TaskCreateViewModel
import com.gondroid.noteai.presentation.screens.voiceRecorder.VoiceRecorderScreenRoot

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
            startDestination = NoteScreenRoute
        ) {
            composable<NoteScreenRoute> {
                val viewmodel = hiltViewModel<NoteScreenViewModel>()
                NotesScreenRoot(
                    viewModel = viewmodel,
                    navigateTo = { noteId ->
                        navController.navigate(
                            NoteCreateScreenRoute(
                                noteId = noteId
                            )
                        )
                    }
                )
            }

            composable<NoteCreateScreenRoute> {
                val viewmodel = hiltViewModel<NoteCreateViewModel>()
                NoteCreateScreenRoot(
                    viewModel = viewmodel,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToVoiceRecorder = {
                        navController.navigate(VoiceRecorderScreen)
                    }
                )
            }


            composable<TaskScreenRoute> {
                val viewmodel = hiltViewModel<TaskScreenViewModel>()
                TaskScreenRoot(
                    viewModel = viewmodel,
                    navigateToTaskCreateScreen = { taskId ->
                        navController.navigate(
                            TaskCreateScreenRoute(
                                taskId = taskId
                            )
                        )
                    }
                )
            }

            composable<TaskCreateScreenRoute> {
                val viewmodel = hiltViewModel<TaskCreateViewModel>()
                TaskCreateScreenRoot(
                    viewModel = viewmodel,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }

            composable<TestScreenRoute> {
                TestScreenRoot()
            }

            composable<VoiceRecorderScreen> {
                VoiceRecorderScreenRoot()
            }
        }
    }
}

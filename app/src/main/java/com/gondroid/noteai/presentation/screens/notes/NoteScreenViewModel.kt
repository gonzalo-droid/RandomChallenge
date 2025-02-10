package com.gondroid.noteai.presentation.screens.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gondroid.noteai.domain.repository.TaskLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val taskLocalDataSource: TaskLocalDataSource
):ViewModel() {

    var state by   mutableStateOf(NoteDataState())
        private set

    private val eventChannel = Channel<NoteScreenEvent>()
    val events = eventChannel.receiveAsFlow()


    fun onAction(action: NoteScreenAction) {
        viewModelScope.launch {

        }
    }
}
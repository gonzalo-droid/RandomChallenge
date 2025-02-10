package com.gondroid.noteai.presentation.screens.noteCreate

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.gondroid.noteai.domain.Note
import com.gondroid.noteai.domain.repository.NoteLocalDataSource
import com.gondroid.noteai.presentation.navigation.NoteCreateScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NoteCreateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val localDataSource: NoteLocalDataSource
) : ViewModel() {

    var state by mutableStateOf(NoteCreateScreenState())
        private set

    private var eventChannel = Channel<NoteCreateEvent>()

    val event = eventChannel.receiveAsFlow()
    private val canSaveNote = snapshotFlow { state.title.text.toString() }
    private val noteData = savedStateHandle.toRoute<NoteCreateScreenRoute>()

    private var editedNote: Note? = null

    init {
        noteData.noteId?.let {
            viewModelScope.launch {
                localDataSource.getNoteById(noteData.noteId)?.let { task ->
                    editedNote = task
                    state = state.copy(
                        title = TextFieldState(task.title),
                        content = TextFieldState(task.content ?: ""),
                        category = if(task.category == null) null else task.category.toString()
                    )
                }
            }
        }

        canSaveNote.onEach {
            state = state.copy(canSaveNote = it.isNotEmpty())
        }.launchIn(viewModelScope)
    }

    fun onAction(action: ActionNoteCreate) {
        viewModelScope.launch {
            when (action) {

                is ActionNoteCreate.ChangeNoteCategory -> {
                    state = state.copy(category = action.category.toString())
                }

                is ActionNoteCreate.SaveNote -> {

                    editedNote?.let {
                        this@NoteCreateViewModel.localDataSource.updateNote(
                            updatedNote = it.copy(
                                id = it.id,
                                title = state.title.text.toString(),
                                content = state.content.text.toString(),
                                category = state.category.toString()
                            )
                        )
                    } ?: run {
                        val note = Note(
                            id = UUID.randomUUID().toString(),
                            title = state.title.text.toString(),
                            content = state.content.text.toString(),
                            category = state.category.toString()
                        )
                        localDataSource.addNote(
                            note = note
                        )
                    }
                    eventChannel.send(NoteCreateEvent.NoteCreated)
                }

                else -> Unit
            }
        }
    }
}
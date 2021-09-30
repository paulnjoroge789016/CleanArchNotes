package com.paul.cleanarchnotes.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.cleanarchnotes.feature_note.domain.model.Note
import com.paul.cleanarchnotes.feature_note.domain.model.NoteState
import com.paul.cleanarchnotes.feature_note.domain.usecase.NoteUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel(){


    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null
    fun onEvent(event: NotesEvent){

        when(event){

            is NotesEvent.Order -> {


            }

            is NotesEvent.DeleteNote -> {

                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {

                }

            }

            is NotesEvent.ToggleOrderSection -> {

                _state.value  = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }


        }

    }
}
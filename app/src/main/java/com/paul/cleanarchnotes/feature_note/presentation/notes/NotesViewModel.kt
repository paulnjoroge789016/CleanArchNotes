package com.paul.cleanarchnotes.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.cleanarchnotes.feature_note.domain.model.Note
import com.paul.cleanarchnotes.feature_note.domain.model.NoteState
import com.paul.cleanarchnotes.feature_note.domain.usecase.NoteUseCases
import com.paul.cleanarchnotes.feature_note.domain.util.NoteOrder
import com.paul.cleanarchnotes.feature_note.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel(){


    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null


    init{
        getNotes(NoteOrder.Date(OrderType.Descending))
    }
    fun onEvent(event: NotesEvent){

        when(event){

            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder ::class &&
                    state.value.noteOrder.orderType ==
                        event.noteOrder.orderType){
                    return

                }
                getNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {

                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {

                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }

            }

            is NotesEvent.ToggleOrderSection -> {

                _state.value  = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }


        }

    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

}
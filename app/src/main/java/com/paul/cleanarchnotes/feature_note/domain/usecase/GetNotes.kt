package com.paul.cleanarchnotes.feature_note.domain.usecase

import com.paul.cleanarchnotes.feature_note.domain.model.Note
import com.paul.cleanarchnotes.feature_note.domain.repository.NoteRepository
import com.paul.cleanarchnotes.feature_note.domain.util.NoteOrder
import com.paul.cleanarchnotes.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotes @Inject constructor(
    private val repository: NoteRepository
){
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {

        return repository.getNotes().map { notes ->

            when(noteOrder.orderType){

                is OrderType.Ascending -> {

                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }

                }
                is OrderType.Descending -> {

                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }

                }
            }

        }
    }
}
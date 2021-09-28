package com.paul.cleanarchnotes.feature_note.presentation.notes

import com.paul.cleanarchnotes.feature_note.domain.model.Note
import com.paul.cleanarchnotes.feature_note.domain.util.OrderType


/**
 * For better understanding this class stores all possible actions that a user can
 * trigger from the UI
 */
sealed class NotesEvent{
    data class Order(val orderType: OrderType): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}

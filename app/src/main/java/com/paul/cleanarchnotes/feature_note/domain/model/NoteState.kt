package com.paul.cleanarchnotes.feature_note.domain.model

import com.paul.cleanarchnotes.feature_note.domain.util.NoteOrder
import com.paul.cleanarchnotes.feature_note.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)

package com.paul.cleanarchnotes.feature_note.presentation.add_edit_note.components

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent{
    data class EnteredEventTitle(val value: String) : AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()
    data class EnteredEventContent(val value: String) : AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeColor(val color: Int): AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()
}


package com.paul.cleanarchnotes.feature_note.presentation.notes.components

import androidx.lifecycle.ViewModel
import com.paul.cleanarchnotes.feature_note.domain.usecase.NoteUseCases
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel(){


}
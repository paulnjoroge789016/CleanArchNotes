package com.paul.cleanarchnotes.feature_note.domain.usecase

import com.paul.cleanarchnotes.feature_note.domain.model.Note
import com.paul.cleanarchnotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val repository: NoteRepository
) {

    suspend  operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
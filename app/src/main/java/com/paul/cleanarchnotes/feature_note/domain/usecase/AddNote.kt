package com.paul.cleanarchnotes.feature_note.domain.usecase

import com.paul.cleanarchnotes.feature_note.domain.model.Note
import com.paul.cleanarchnotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class AddNote @Inject constructor(
    private val  repository: NoteRepository
        ){

    suspend operator fun invoke(note: Note) {

        if(note.title.isBlank()){

        }
        repository.insertNote(note)
    }
}
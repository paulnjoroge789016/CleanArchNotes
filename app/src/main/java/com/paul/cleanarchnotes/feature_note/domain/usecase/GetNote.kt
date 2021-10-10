package com.paul.cleanarchnotes.feature_note.domain.usecase

import com.paul.cleanarchnotes.feature_note.domain.model.Note
import com.paul.cleanarchnotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class GetNote @Inject constructor(
    private val repository: NoteRepository
) {


    suspend fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}
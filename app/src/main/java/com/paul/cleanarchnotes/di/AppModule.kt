package com.paul.cleanarchnotes.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paul.cleanarchnotes.feature_note.data.data_source.NoteDatabase
import com.paul.cleanarchnotes.feature_note.data.repository.NoteRepositoryImpl
import com.paul.cleanarchnotes.feature_note.domain.repository.NoteRepository
import com.paul.cleanarchnotes.feature_note.domain.usecase.DeleteNote
import com.paul.cleanarchnotes.feature_note.domain.usecase.GetNotes
import com.paul.cleanarchnotes.feature_note.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()

    }


    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase) : NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }


    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository) : NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository = repository),
            deleteNote = DeleteNote(repository = repository)
        )
    }


}
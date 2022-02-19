package com.paul.cleanarchnotes.feature_note.data.data_source

import android.graphics.Color
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.paul.cleanarchnotes.R
import com.paul.cleanarchnotes.feature_note.domain.model.Note
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var noteDatabase: NoteDatabase
    lateinit var noteDao: NoteDao
    lateinit var notes: List<Note>

    @Before
    fun setup() {
        noteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()


        noteDao = noteDatabase.noteDao


    }


    @Test
    fun insertShoppingItem() = runBlockingTest {

        val color = R.color.teal_700
        val note = Note(
            id = 1,
            title = "Test",
            content = "Test",
            timestamp = 1645105761,
            color
        )
        noteDao.insertNote(note)

        noteDao.getNotes().test {
            notes = awaitItem()
            assertThat(notes).contains(note)
            cancelAndConsumeRemainingEvents()
        }

    }


    @Test
    fun getNoteById() = runBlockingTest {
        val color = R.color.teal_700
        val note = Note(
            id = 1,
            title = "Test",
            content = "Test",
            timestamp = 1645105761,
            color
        )
        noteDao.insertNote(note)

        val noteFromDb = noteDao.getNoteById(1)
        assertThat(note).isEqualTo(noteFromDb)

    }

    @Test
    fun deleteNote() = runBlockingTest {

        val color = R.color.teal_700
        val note = Note(
            id = 1,
            title = "Test",
            content = "Test",
            timestamp = 1645105761,
            color
        )
        noteDao.insertNote(note)
        noteDao.deleteNote(note)

        noteDao.getNotes().test {
            notes = awaitItem()
            assertThat(notes.size).isEqualTo(0)
            cancelAndConsumeRemainingEvents()
        }


    }

    @After
    fun tearDown() {
        noteDatabase.close()
    }
}
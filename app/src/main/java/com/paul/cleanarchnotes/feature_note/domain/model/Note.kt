package com.paul.cleanarchnotes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paul.cleanarchnotes.ui.theme.*
import java.lang.Exception

@Entity
data class Note(
    @PrimaryKey val id: Int?,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
){


    companion object {
        val noteColors= listOf( RedOrange, LightGreen, Violet, BabyBlue, RedPink)

    }
}


class  InvalidNoteException(message: String): Exception(message)

package com.paul.cleanarchnotes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paul.cleanarchnotes.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
){
    @PrimaryKey val id: Int = 0
    val noteColors= listOf( RedOrange, LightGreen, Violet, BabyBlue, RedPink)
}


class  Invla

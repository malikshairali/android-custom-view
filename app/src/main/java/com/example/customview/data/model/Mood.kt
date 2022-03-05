package com.example.customview.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_table")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mood_id")
    val moodId: Long = 0L,

    @ColumnInfo(name = "mood")
    val mood: Long,

    @ColumnInfo(name = "date_and_time")
    val dateAndTime: String
)
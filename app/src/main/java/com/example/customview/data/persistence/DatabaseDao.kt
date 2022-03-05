package com.example.customview.data.persistence

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.customview.data.model.Mood

@Dao
interface DatabaseDao {

    @Insert
    fun insert(mood: Mood)

    @Query("SELECT * FROM mood_table ORDER BY mood_id DESC")
    fun getMoods(): PagingSource<Int, Mood>
}
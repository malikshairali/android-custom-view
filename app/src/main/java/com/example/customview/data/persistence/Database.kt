package com.example.customview.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.customview.data.model.Mood

@Database(entities = [Mood::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract val databaseDao: DatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: com.example.customview.data.persistence.Database? = null

        fun getInstance(context: Context): com.example.customview.data.persistence.Database {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.customview.data.persistence.Database::class.java,
                        "mood_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}
package com.mohit.todonotesapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohit.todonotesapp.data.local.db.dao.NotesDao
import com.mohit.todonotesapp.data.local.db.entity.NotesEntity

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object {
        lateinit var INSTANCE : NotesDatabase

        fun getInstance(context: Context): NotesDatabase {
            synchronized(NotesDatabase::class) {
                INSTANCE = Room.databaseBuilder(context, NotesDatabase::class.java, "my-notes.db")
                    .allowMainThreadQueries() // Temporarily allow access from UI Thread
                    .build()
            }

            return INSTANCE
        }
    }
}
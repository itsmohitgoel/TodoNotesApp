package com.mohit.todonotesapp.data.local.db.dao

import androidx.room.*
import com.mohit.todonotesapp.data.local.db.entity.NotesEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NotesEntity)

    @Update
    fun updateNote(note: NotesEntity)

    @Delete
    fun deleteNote(note: NotesEntity)

    @Query("SELECT * FROM notesdata")
    fun getAllNotes(): List<NotesEntity>
}
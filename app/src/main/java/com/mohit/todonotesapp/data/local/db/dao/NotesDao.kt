package com.mohit.todonotesapp.data.local.db.dao

import androidx.room.*
import com.mohit.todonotesapp.data.model.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM notesdata")
    fun getAllNotes(): List<Note>
}
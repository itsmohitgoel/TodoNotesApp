package com.mohit.todonotesapp.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *   ______________________________________________________________
 *  |                        `notesdata`                           |
 *  | _____________________________________________________________|
 *  | id    |  title   | description | imagePath | isTaskCompleted |
 *  |(Long) | (String) | (String)    | (String)  |    (Boolean)    |
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


@Entity(tableName = "notesdata")

data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "imagePath")
    val imagPath: String = "",

    @ColumnInfo(name = "isTaskCompleted")
    var isTaskCompleted: Boolean = false
)
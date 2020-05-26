package com.mohit.todonotesapp

import android.app.Application
import com.mohit.todonotesapp.data.local.db.NotesDatabase

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    fun getNotesDb(): NotesDatabase =
        NotesDatabase.getInstance(this)
}
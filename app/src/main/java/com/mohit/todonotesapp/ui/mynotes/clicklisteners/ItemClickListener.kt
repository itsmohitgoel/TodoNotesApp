package com.mohit.todonotesapp.ui.mynotes.clicklisteners

import com.mohit.todonotesapp.data.local.db.entity.NotesEntity

interface ItemClickListener {
    fun onClick(note: NotesEntity)
    fun onUpdate(note : NotesEntity)
}
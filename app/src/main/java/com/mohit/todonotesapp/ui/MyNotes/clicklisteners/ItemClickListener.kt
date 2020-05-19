package com.mohit.todonotesapp.ui.MyNotes.clicklisteners

import com.mohit.todonotesapp.data.model.Note

interface ItemClickListener {
    fun onClick(note: Note)
}
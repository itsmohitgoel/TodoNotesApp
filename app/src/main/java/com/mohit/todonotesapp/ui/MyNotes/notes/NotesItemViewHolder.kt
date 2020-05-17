package com.mohit.todonotesapp.ui.MyNotes.notes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemview_notes.view.*

class NotesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
        val tvTitle = itemView.tvTitle
        val tvDescription = itemView.tvDescription
    }
}
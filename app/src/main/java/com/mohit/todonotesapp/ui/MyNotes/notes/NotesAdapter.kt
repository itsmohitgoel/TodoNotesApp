package com.mohit.todonotesapp.ui.MyNotes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.data.model.Note
import kotlinx.android.synthetic.main.itemview_notes.view.*

class NotesAdapter(val notesList: MutableList<Note>) : RecyclerView.Adapter<NotesItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.itemview_notes, parent, false)
        return NotesItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesItemViewHolder, position: Int) {
        holder.itemView.tvTitle.text = notesList[position].title
        holder.itemView.tvDescription.text = notesList[position].description
    }
}
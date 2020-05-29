package com.mohit.todonotesapp.ui.mynotes.notes

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemview_notes.view.*

class NotesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView
    val tvDescription: TextView
    val adapterMarkStatus: CheckBox

    init {
        tvTitle = itemView.tvTitle
        tvDescription = itemView.tvDescription
        adapterMarkStatus = itemView.checkBoxMarkStatus
    }
}
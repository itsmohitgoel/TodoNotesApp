package com.mohit.todonotesapp.ui.mynotes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.data.local.db.entity.NotesEntity
import com.mohit.todonotesapp.ui.mynotes.clicklisteners.ItemClickListener

class NotesAdapter(val notesList: MutableList<NotesEntity>, val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<NotesItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.itemview_notes, parent, false)
        return NotesItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesItemViewHolder, position: Int) {
        val noteItem = notesList[position]
        holder.tvTitle.text = noteItem.title
        holder.tvDescription.text = noteItem.description
        holder.adapterMarkStatus.isChecked = noteItem.isTaskCompleted

        holder.itemView.setOnClickListener { itemClickListener.onClick(noteItem) }

        holder.adapterMarkStatus.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                 noteItem.isTaskCompleted = p1
                itemClickListener.onUpdate(noteItem)
            }
        })
    }
}
package com.mohit.todonotesapp.ui.addnotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohit.todonotesapp.R
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        bindViews()
        setUpClickListeners()
        ivAddNotes.setOnClickListener {
            setupDialog()
        }
    }

    private fun bindViews() {
        TODO("Not Implemented Yet")
    }

    private fun setUpClickListeners() {
        TODO("Not Implemented Yet")
    }

    private fun setupDialog() {
        TODO("Not Implemented Yet")
    }
}
package com.mohit.todonotesapp.ui.MyNotes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.utils.common.Constants
import kotlinx.android.synthetic.main.activity_my_notes.*
import kotlinx.android.synthetic.main.layout_dialog_add_my_notes.*
import kotlinx.android.synthetic.main.layout_dialog_add_my_notes.view.*

class MyNotesActivity : AppCompatActivity() {

    private lateinit var textViewMyNotesTitle: TextView
    private lateinit var textViewMyNotesDescription: TextView
    private lateinit var floatingButtonAddNotes: FloatingActionButton
    private var fullName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)

        bindViews()
        getIntentData()

        floatingButtonAddNotes.setOnClickListener {
            setUpDialog()
        }

        supportActionBar?.title = fullName
    }


    private fun bindViews() {
        textViewMyNotesTitle = tvMyNotesTitle
        textViewMyNotesDescription = tvMyNotesDescription
        floatingButtonAddNotes = fabAddNotes
    }


    private fun getIntentData() {
        fullName = intent.run {
            getStringExtra(Constants.FULL_NAME)
        }
    }



    private fun setUpDialog() {
        val view = LayoutInflater.from(this@MyNotesActivity)
            .inflate(R.layout.layout_dialog_add_my_notes, null)

        val editTextTitle = view.etDialogTitle
        val editTextDesciption = view.etDialogDesciption
        val buttonSubmit = view.btnSubmitDialog

        val alertDialog: AlertDialog = AlertDialog.Builder(this@MyNotesActivity)
            .setView(view).setCancelable(false).create()

        buttonSubmit.setOnClickListener {
            textViewMyNotesTitle.text = editTextTitle.text.toString()
            textViewMyNotesDescription.text = editTextDesciption.text.toString()

            alertDialog.hide()

        }

        alertDialog.show()

    }
}
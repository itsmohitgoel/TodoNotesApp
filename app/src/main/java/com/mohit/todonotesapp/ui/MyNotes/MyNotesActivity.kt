package com.mohit.todonotesapp.ui.MyNotes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.data.model.Note
import com.mohit.todonotesapp.ui.MyNotes.clicklisteners.ItemClickListener
import com.mohit.todonotesapp.ui.MyNotes.notes.NotesAdapter
import com.mohit.todonotesapp.ui.detail.DetailActivity
import com.mohit.todonotesapp.utils.common.Constants
import com.mohit.todonotesapp.utils.common.PrefConstant
import kotlinx.android.synthetic.main.activity_my_notes.*
import kotlinx.android.synthetic.main.layout_dialog_add_my_notes.view.*

class MyNotesActivity : AppCompatActivity() {

    private lateinit var floatingButtonAddNotes: FloatingActionButton
    private var fullName: String? = null
    private lateinit var prefs: SharedPreferences
    private lateinit var recyclerView: RecyclerView

    val dataList: MutableList<Note> = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        setupSharedPreferences()

        bindViews()
        getIntentData()
        setUpRecyclerView(dataList)

        floatingButtonAddNotes.setOnClickListener {
            setUpDialog()
        }

        supportActionBar?.title = fullName
    }


    private fun bindViews() {
        recyclerView = rv_notes
        floatingButtonAddNotes = fabAddNotes
    }


    private fun getIntentData() {
        fullName = intent.run {
            getStringExtra(Constants.FULL_NAME)
        }
        if (TextUtils.isEmpty(fullName)) {
            fullName = prefs.getString(PrefConstant.KEY_FULLNAME, " ")
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
            val title = editTextTitle.text.toString()
            val description = editTextDesciption.text.toString()


            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                dataList.add(Note(title, description))
            } else {
                Toast.makeText(this, "Can't Create a Empty Note", Toast.LENGTH_SHORT).show()
            }

            alertDialog.hide()
        }

        alertDialog.show()

    }

    private fun setupSharedPreferences() {
        prefs = getSharedPreferences(PrefConstant.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun setUpRecyclerView(dataList: MutableList<Note>) {
        val clickListener = object : ItemClickListener {
            override fun onClick(note: Note) {
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(Constants.TITLE, note.title)
                intent.putExtra(Constants.DESCRIPTION, note.description)
                startActivity(intent)
            }
        }

        val adapter: NotesAdapter = NotesAdapter(dataList, clickListener)
        val llManager: LinearLayoutManager = LinearLayoutManager(this@MyNotesActivity)
        llManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = llManager
        recyclerView.adapter = adapter
    }

}
package com.mohit.todonotesapp.ui.mynotes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohit.todonotesapp.NotesApp
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.data.local.db.entity.NotesEntity
import com.mohit.todonotesapp.ui.addnotes.AddNotesActivity
import com.mohit.todonotesapp.ui.mynotes.clicklisteners.ItemClickListener
import com.mohit.todonotesapp.ui.mynotes.notes.NotesAdapter
import com.mohit.todonotesapp.ui.detail.DetailActivity
import com.mohit.todonotesapp.utils.common.Constants
import com.mohit.todonotesapp.utils.common.PrefConstant
import com.mohit.todonotesapp.workmanager.MyWorker
import kotlinx.android.synthetic.main.activity_my_notes.*
import kotlinx.android.synthetic.main.layout_dialog_add_my_notes.view.*
import java.util.concurrent.TimeUnit

class MyNotesActivity : AppCompatActivity() {

    private lateinit var floatingButtonAddNotes: FloatingActionButton
    private var fullName: String? = null
    private lateinit var prefs: SharedPreferences
    private lateinit var recyclerView: RecyclerView
    val REQUEST_CODE_ADD_NOTE = 200

    val dataList: MutableList<NotesEntity> = mutableListOf<NotesEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        setupSharedPreferences()

        bindViews()
        getIntentData()
        getNotesFromDb()
        setupClickListeners()
        setupActionBarText()
        setUpRecyclerView()
        setUpWorkManager()
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
                dataList.add(NotesEntity(title = title, description = description))
                addNoteToDb(NotesEntity(title = title, description = description))
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

    private fun setUpRecyclerView() {
        val clickListener = object : ItemClickListener {
            override fun onClick(note: NotesEntity) {
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(Constants.TITLE, note.title)
                intent.putExtra(Constants.DESCRIPTION, note.description)
                startActivity(intent)
            }

            override fun onUpdate(noteEntity: NotesEntity) {
                val notesapp = applicationContext as NotesApp
                val notesDao = notesapp.getNotesDb().notesDao()

                notesDao.updateNote(noteEntity)
            }
        }

        val adapter: NotesAdapter = NotesAdapter(dataList, clickListener)
        val llManager: LinearLayoutManager = LinearLayoutManager(this@MyNotesActivity)
        llManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = llManager
        recyclerView.adapter = adapter
    }

    private fun addNoteToDb(noteEntity: NotesEntity) {
        val notesapp: NotesApp = applicationContext as NotesApp
        val notesDao = notesapp.getNotesDb().notesDao()
        notesDao.insertNote(noteEntity)

    }

    private fun getNotesFromDb() {
        val notesapp = applicationContext as NotesApp
        val notesDao = notesapp.getNotesDb().notesDao()

        dataList.addAll(notesDao.getAllNotes())
    }

    private fun setupClickListeners() {
        floatingButtonAddNotes.setOnClickListener {
            val intent = Intent(this, AddNotesActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE)
        }
    }

    private fun setupActionBarText() {
        supportActionBar?.title = fullName
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_ADD_NOTE -> {
                    val title = resultData?.getStringExtra(Constants.TITLE)
                    val description = resultData?.getStringExtra(Constants.DESCRIPTION)
                    val imagePath = resultData?.getStringExtra(Constants.IMAGE_PATH)

                    val noteEntity: NotesEntity = NotesEntity(
                        title = title!!,
                        description = description!!,
                        imagPath = imagePath!!,
                        isTaskCompleted = false
                    )

                    addNoteToDb(noteEntity)
                    dataList.add(noteEntity)
                    recyclerView?.adapter?.notifyItemChanged(dataList.size - 1)
                }
            }
        }
    }

    private fun setUpWorkManager() {
        val constraint = Constraints.Builder().build()
        val workRequest = PeriodicWorkRequest
            .Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()

        val workManager: WorkManager = WorkManager.getInstance()
        workManager.enqueue(workRequest)
    }
}
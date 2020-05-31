package com.mohit.todonotesapp.ui.addnotes

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.mohit.todonotesapp.R
import kotlinx.android.synthetic.main.layout_dialog_selector.view.*

class AddNotesActivity : AppCompatActivity() {

    lateinit var etNoteTitle: EditText
    lateinit var etNoteDescription: EditText
    lateinit var btnSubmit: MaterialButton
    lateinit var ivAddNotes: ImageView
    val REQUEST_CODE_GALLERY = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        bindViews()
        setUpClickListeners()
    }

    private fun bindViews() {
        etNoteTitle = findViewById(R.id.etNoteTitle)
        etNoteDescription = findViewById(R.id.etNoteDescription)
        btnSubmit = findViewById(R.id.btnSubmit)
        ivAddNotes = findViewById(R.id.ivAddNoteImage)
    }

    private fun setUpClickListeners() {
        ivAddNotes.setOnClickListener {
            setupDialog()
        }
    }

    private fun setupDialog() {
        val view = LayoutInflater.from(this)
            .inflate(R.layout.layout_dialog_selector, null)

        val tvDialogCamera = view.tvDialogCamera
        val tvDialogGallery = view.tvDialogGallery

        val dialog: AlertDialog = AlertDialog.Builder(this)
            .setView(view).setCancelable(false).create()

        tvDialogCamera.setOnClickListener {
            TODO("NOT YET Implemented")
        }

        tvDialogGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE_GALLERY)
        }

        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
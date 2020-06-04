package com.mohit.todonotesapp.ui.addnotes

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.mohit.todonotesapp.R
import kotlinx.android.synthetic.main.layout_dialog_selector.view.*

class AddNotesActivity : AppCompatActivity() {

    lateinit var etNoteTitle: EditText
    lateinit var etNoteDescription: EditText
    lateinit var btnSubmit: MaterialButton
    lateinit var ivAddNotes: ImageView
    val REQUEST_CODE_GALLERY = 2
    val REQUEST_CODE_CAMERA = 1
    val REQUEST_CODE_PERMISSION = 124
    var imagePath = ""

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
            if (checkAndRequestPermission())
                setupDialog()
        }
    }

    private fun setupDialog() {
        val view = LayoutInflater.from(this)
            .inflate(R.layout.layout_dialog_selector, null)

        val tvDialogCamera = view.tvDialogCamera
        val tvDialogGallery = view.tvDialogGallery

        val permissionDialog: AlertDialog = AlertDialog.Builder(this)
            .setView(view).setCancelable(false).create()

        tvDialogCamera.setOnClickListener {
            TODO("NOT YET Implemented")
        }

        tvDialogGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE_GALLERY)

            permissionDialog.hide()
        }

        permissionDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_GALLERY -> {
                    val selectedImage = data?.data
                    val filePath = arrayOf(MediaStore.Images.Media.DATA)
                    val contentResolver = contentResolver.query(
                        selectedImage!!, filePath, null, null, null
                    )
                    contentResolver!!.moveToFirst()
                    val columnIndex = contentResolver.getColumnIndex(filePath[0])
                    imagePath = contentResolver.getString(columnIndex)
                    contentResolver.close()
                    Glide.with(this).load(imagePath).into(ivAddNotes)
                }
                REQUEST_CODE_CAMERA -> {

                }
            }
        }
    }

    private fun checkAndRequestPermission(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val externalStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

        val listPermissionNeeded = ArrayList<String>()

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(Manifest.permission.CAMERA)
        }

        if (externalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (listPermissionNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toTypedArray(), REQUEST_CODE_PERMISSION)
            return false
        }

        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    setupDialog()
                else {
                    Toast.makeText(
                        this@AddNotesActivity, "Permission is needed to browse image or click new image",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
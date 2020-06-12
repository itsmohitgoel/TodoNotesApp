package com.mohit.todonotesapp.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mohit.todonotesapp.NotesApp

class MyWorker(ctx: Context, workerParams: WorkerParameters) : Worker(ctx, workerParams) {

    override fun doWork(): Result {
        val notesApp = applicationContext as NotesApp
        val notesDao = notesApp.getNotesDb().notesDao()
        notesDao.deleteNotesWhenCompleted(true)
        return Result.success()
    }
}
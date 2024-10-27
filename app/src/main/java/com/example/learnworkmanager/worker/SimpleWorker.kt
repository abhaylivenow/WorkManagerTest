package com.example.learnworkmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.learnworkmanager.NotificationHelper

class SimpleWorker(val context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        NotificationHelper.showNotification(context)
        return Result.success()
    }
}
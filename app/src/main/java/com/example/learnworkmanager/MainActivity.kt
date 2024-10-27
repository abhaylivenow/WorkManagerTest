package com.example.learnworkmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.learnworkmanager.ui.theme.LearnWorkManagerTheme
import com.example.learnworkmanager.worker.SimpleWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationHelper.createNotificationChannel(this)
        setContent {

            val workManager = remember {
                WorkManager.getInstance(this)
            }

            WorkerScreenUI({
                val workRequest = OneTimeWorkRequestBuilder<SimpleWorker>()
                    .setInitialDelay(20, TimeUnit.SECONDS)
                    .build()
                workManager.enqueue(workRequest)
            })
        }
    }
}

@Composable
fun WorkerScreenUI(activateWorkerClickAction:() -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            activateWorkerClickAction()
        }) {
            Text(text = "Activate Worker")
        }
    }
}
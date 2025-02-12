package com.gondroid.noteai.presentation.screens.voiceRecorder

import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun VoiceRecorderScreenRoot() {
    var isRecording by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableStateOf(0L) }
    val mediaRecorder = remember { mutableStateOf<MediaRecorder?>(null) }
    val timer = remember { mutableStateOf<Job?>(null) }

    val startRecording: () -> Unit = {
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        if (!directory.exists()) directory.mkdirs()
        var outputFile = "${directory.absolutePath}/voice_note_${System.currentTimeMillis()}.mp3"

        try {
            mediaRecorder.value?.release() // Liberar si ya existía
            mediaRecorder.value = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(outputFile)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                prepare()
                start()
            }
            isRecording = true
            isPaused = false
            timer.value?.cancel()
            timer.value = CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    delay(1000)
                    elapsedTime++
                }
            }
        } catch (e: Exception) {
            Log.e("VoiceRecorder", "Error al iniciar la grabación: ${e.message}")
        }
    }

    val stopRecording: () -> Unit = {
        try {
            mediaRecorder.value?.apply {
                stop()
                release()
            }
            mediaRecorder.value = null
            isRecording = false
            isPaused = false
            timer.value?.cancel()
            elapsedTime = 0
        } catch (e: Exception) {
            Log.e("VoiceRecorder", "Error al detener la grabación: ${e.message}")
        }
    }

    val pauseRecording: () -> Unit = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mediaRecorder.value?.pause()
            isPaused = true
            timer.value?.cancel()
        }
    }

    val resumeRecording: () -> Unit = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mediaRecorder.value?.resume()
            isPaused = false
            timer.value = CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    delay(1000)
                    elapsedTime++
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = String.format("%02d:%02d", elapsedTime / 60, elapsedTime % 60),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Simulación de la visualización de audio grabado
        AnimatedVisibility(visible = isRecording) {
            Box(
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((elapsedTime.toInt() % 200).dp)
                        .background(Color.Red)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            IconButton(
                onClick = { if (isRecording) pauseRecording() else startRecording() },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = if (isRecording && !isPaused) Icons.Default.Pause else Icons.Default.Mic,
                    contentDescription = if (isRecording) "Pausar grabación" else "Iniciar grabación",
                    tint = Color.White,
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Red, CircleShape)
                        .padding(12.dp)
                )
            }

            if (isRecording) {
                IconButton(
                    onClick = { stopRecording() },
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = "Detener grabación",
                        tint = Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Black, CircleShape)
                            .padding(12.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVoiceRecorderScreen() {
    VoiceRecorderScreenRoot()
}

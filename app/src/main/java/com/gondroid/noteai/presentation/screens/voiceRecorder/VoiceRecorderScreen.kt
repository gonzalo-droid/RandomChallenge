package com.gondroid.noteai.presentation.screens.voiceRecorder

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gondroid.noteai.ui.theme.NoteAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun VoiceRecorderScreenRoot() {
    var isRecording by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableStateOf(0L) }
    val timer = remember { mutableStateOf<Job?>(null) }
    var amplitudes by remember { mutableStateOf(listOf<Float>()) }
    val audioRecorder = remember { AudioRecorder() }
    var recordedFilePath by remember { mutableStateOf<String?>(null) }

    /*
    val startRecording: () -> Unit = {
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        if (!directory.exists()) directory.mkdirs()
        val outputFile = "${directory.absolutePath}/voice_note_${System.currentTimeMillis()}.mp3"

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

                    val newAmplitude =
                        Random.nextDouble(0.2, 1.0).toFloat() // Valor aleatorio para simular audio
                    amplitudes =
                        (amplitudes + newAmplitude).takeLast(50) // Mantiene las últimas 50 muestras
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
        mediaRecorder.value?.pause()
        isPaused = true
        timer.value?.cancel()
    }

    val resumeRecording: () -> Unit = {
        mediaRecorder.value?.resume()

        isPaused = false
        timer.value = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)
                elapsedTime++
            }
        }
    }
    */

    VoiceRecorderScreen(
        elapsedTime = elapsedTime,
        isRecording = isRecording,
        isPaused = isPaused,
        audioRecorder = audioRecorder,
        stopRecording = {
            audioRecorder.stopRecording()
            isRecording = false
            isPaused = false
            timer.value?.cancel()
            elapsedTime = 0
        },
        pauseRecording = {
            audioRecorder.pauseRecording()
            isPaused = true
            timer.value?.cancel()
        },
        startRecording = {
            recordedFilePath = audioRecorder.startRecording()

            isRecording = true
            isPaused = false
            timer.value?.cancel()
            timer.value = CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    delay(1000)
                    elapsedTime++

                    val newAmplitude =
                        Random.nextDouble(0.2, 1.0).toFloat() // Valor aleatorio para simular audio
                    amplitudes =
                        (amplitudes + newAmplitude).takeLast(50) // Mantiene las últimas 50 muestras
                }
            }
        },
        resumeRecording = {
            audioRecorder.resumeRecording()
            isPaused = false
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoiceRecorderScreen(
    elapsedTime: Long,
    isPaused: Boolean,
    isRecording: Boolean,
    audioRecorder: AudioRecorder,
    stopRecording: () -> Unit,
    pauseRecording: () -> Unit,
    startRecording: () -> Unit,
    resumeRecording: () -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notes.AI",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = String.format("%02d:%02d", elapsedTime / 60, elapsedTime % 60),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Visualizador de audio
            /*
            AudioRecorderVisualizer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                isRecording = isRecording
            )
            */

            AudioVisualizer(audioRecorder, Modifier.fillMaxWidth().height(50.dp))

            Spacer(modifier = Modifier.height(40.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                if (isRecording) {
                    IconButton(
                        onClick = { stopRecording() },
                        modifier = Modifier.size(64.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Stop record",
                            tint = Color.White,
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.Gray, CircleShape)
                                .padding(12.dp)
                        )
                    }
                }

                if(isRecording){
                    IconButton(
                        onClick = { if (isPaused) pauseRecording() else resumeRecording() },
                        modifier = Modifier.size(64.dp)
                    ) {
                        Icon(
                            imageVector = if (isPaused) Icons.Default.Pause else Icons.Default.Mic,
                            contentDescription = if (isPaused) "Pause record" else "Start record",
                            tint = Color.White,
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.Red, CircleShape)
                                .padding(12.dp)
                        )
                    }
                }

                if (!isRecording) {
                    IconButton(
                        onClick = { startRecording() },
                        modifier = Modifier.size(64.dp)
                    ) {
                        Icon(
                            imageVector =  Icons.Default.Mic,
                            contentDescription = "Start record",
                            tint = Color.White,
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.Red, CircleShape)
                                .padding(12.dp)
                        )
                    }
                }


                if (isRecording) {
                    IconButton(
                        onClick = { stopRecording() },
                        modifier = Modifier.size(64.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save record",
                            tint = Color.White,
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.Gray, CircleShape)
                                .padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AudioRecorderVisualizer(modifier: Modifier = Modifier, isRecording: Boolean) {
    var amplitudes by remember { mutableStateOf(List(50) { 0.1f }) }

    LaunchedEffect(isRecording) {
        while (isRecording) {
            amplitudes = amplitudes.drop(1) + Random.nextFloat()
            delay(100) // Simula la frecuencia de actualización
        }
    }

    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(100.dp)) {
        val barWidth = size.width / amplitudes.size
        val maxHeight = size.height

        amplitudes.forEachIndexed { index, amplitude ->
            val barHeight = maxHeight * amplitude
            drawRect(
                color = Color(0xFFE65100), // Naranja fuerte
                topLeft = Offset(index * barWidth, (maxHeight - barHeight) / 2),
                size = Size(barWidth * 0.8f, barHeight),
                alpha = 0.8f
            )
        }
    }
}


@Composable
fun AudioVisualizer(audioRecorder: AudioRecorder, modifier: Modifier = Modifier) {
    val amplitudes by audioRecorder.getAmplitudeFlow().collectAsState(initial = List(50) { 0.1f })

    Canvas(modifier = modifier.fillMaxWidth().height(200.dp)) {
        val barWidth = size.width / amplitudes.size
        val maxHeight = size.height

        amplitudes.forEachIndexed { index, amplitude ->
            val barHeight = maxHeight * amplitude
            drawRoundRect(
                color = Color(0xFFE65100),
                topLeft = Offset(index * barWidth, (maxHeight - barHeight) / 2),
                size = Size(barWidth * 0.5f, barHeight),
                cornerRadius = CornerRadius(4f, 4f)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewVoiceRecorderScreen() {
    NoteAppTheme {
        VoiceRecorderScreen(
            elapsedTime = 0L,
            isRecording = false,
            isPaused = false,
            audioRecorder = AudioRecorder(),
            stopRecording = {},
            pauseRecording = {},
            startRecording = {},
            resumeRecording = {}
        )
    }
}

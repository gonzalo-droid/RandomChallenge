package com.gondroid.noteai.presentation

import android.media.MediaRecorder
import android.os.Environment
import android.util.Log

class AudioRecorder {
    private var mediaRecorder: MediaRecorder? = null
    private var outputFile: String = ""

    fun startRecording(): String? {
        try {
            // Definir la ruta correcta en Android 10+
            val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
            if (!directory.exists()) directory.mkdirs()

            outputFile = "${directory.absolutePath}/voice_note_${System.currentTimeMillis()}.mp3"

            mediaRecorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(outputFile)

                prepare()
                start()
            }
            return outputFile
        } catch (e: Exception) {
            Log.e("AudioRecorder", "Error al iniciar la grabación", e)
            return null
        }
    }

    fun stopRecording() {
        try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            mediaRecorder = null
        } catch (e: Exception) {
            Log.e("AudioRecorder", "Error al detener la grabación", e)
        }
    }
}

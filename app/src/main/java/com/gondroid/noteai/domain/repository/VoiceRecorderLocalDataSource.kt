package com.gondroid.noteai.domain.repository

import com.gondroid.noteai.domain.VoiceRecorder
import kotlinx.coroutines.flow.Flow

interface VoiceRecorderLocalDataSource {
    val voiceRecordingsFlow: Flow<List<VoiceRecorder>>

    suspend fun addVoiceRecorder(voiceRecorder: VoiceRecorder)

    suspend fun updateVoiceRecorder(updatedVoiceRecorder: VoiceRecorder)

    suspend fun removeVoiceRecorder(voiceRecorder: VoiceRecorder)

    suspend fun deleteAllVoiceRecorders()

    suspend fun getVoiceRecorderById(id: String): VoiceRecorder?

    suspend fun getVoiceRecorderByNoteId(noteId: String): VoiceRecorder?
}

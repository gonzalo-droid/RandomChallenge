package com.gondroid.noteai.data.local.voiceRecorder

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface VoiceRecorderDao {
    @Query("SELECT * FROM voice_recorder")
    fun getAllVoiceRecordings(): Flow<List<VoiceRecorderEntity>>

    @Query("SELECT * FROM voice_recorder WHERE id = :id")
    suspend fun getVoiceRecorderById(id: String): VoiceRecorderEntity?

    @Query("SELECT * FROM voice_recorder WHERE note_id = :noteId")
    suspend fun getVoiceRecorderByNoteId(noteId: String): VoiceRecorderEntity?

    @Upsert
    suspend fun upsertVoiceRecorder(voiceRecorder: VoiceRecorderEntity)

    @Query("DELETE FROM voice_recorder WHERE id = :id")
    suspend fun deleteVoiceRecorderById(id: String)

    @Query("DELETE FROM voice_recorder")
    suspend fun deleteAllVoiceRecorders()
}

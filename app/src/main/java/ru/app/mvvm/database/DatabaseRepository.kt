package ru.app.mvvm.database

import androidx.lifecycle.LiveData
import ru.app.mvvm.models.Note

interface DatabaseRepository {
    val allNotes: LiveData<List<Note>>
    suspend fun insert(note: Note, onSuccess: () -> Unit)
    suspend fun delete(note: Note, onSuccess: () -> Unit)

    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {

    }

    fun signOut() {}

}
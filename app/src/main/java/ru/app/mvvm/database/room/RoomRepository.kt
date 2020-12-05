package ru.app.mvvm.database.room


import androidx.lifecycle.LiveData
import ru.app.mvvm.database.DatabaseRepository
import ru.app.mvvm.models.Note

class RoomRepository(private val appRoomDao: AppRoomDao) : DatabaseRepository {


    override val allNotes: LiveData<List<Note>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: Note, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

    override fun signOut() {
        super.signOut()
    }


}
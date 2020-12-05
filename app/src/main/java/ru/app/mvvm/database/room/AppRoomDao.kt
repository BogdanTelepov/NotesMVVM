package ru.app.mvvm.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import ru.app.mvvm.models.Note


@Dao
interface AppRoomDao {
    @Query("SELECT * from notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

}
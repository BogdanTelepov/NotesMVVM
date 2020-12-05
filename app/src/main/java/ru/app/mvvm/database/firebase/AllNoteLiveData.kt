package ru.app.mvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ru.app.mvvm.models.Note
import ru.app.mvvm.utilits.REF_DATABASE

class AllNoteLiveData : LiveData<List<Note>>() {


    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(Note::class.java) ?: Note()
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }

    }


    override fun onActive() {
        REF_DATABASE.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        REF_DATABASE.removeEventListener(listener)
        super.onInactive()
    }
}
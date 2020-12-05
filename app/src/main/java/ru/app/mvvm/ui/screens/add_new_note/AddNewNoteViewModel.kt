package ru.app.mvvm.ui.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.app.mvvm.models.Note
import ru.app.mvvm.utilits.REPOSITORY

class AddNewNoteViewModel(application: Application) : AndroidViewModel(application) {


    fun insert(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note) {
                onSuccess()
            }
        }
    }

}
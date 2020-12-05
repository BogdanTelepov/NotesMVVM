package ru.app.mvvm.ui.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.app.mvvm.models.Note
import ru.app.mvvm.utilits.REPOSITORY

class NoteFragmentViewModel(application: Application) : AndroidViewModel(application) {

    fun delete(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note) {
                onSuccess()
            }
        }
    }
}
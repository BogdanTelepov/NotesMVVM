package ru.app.mvvm.ui.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.app.mvvm.utilits.REPOSITORY

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes
    fun signOut(){
        REPOSITORY.signOut()
    }

}
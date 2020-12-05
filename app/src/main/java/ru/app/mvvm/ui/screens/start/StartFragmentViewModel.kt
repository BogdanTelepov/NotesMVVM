package ru.app.mvvm.ui.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.app.mvvm.database.firebase.AppFirebaseRepository
import ru.app.mvvm.database.room.AppRoomDatabase
import ru.app.mvvm.database.room.RoomRepository
import ru.app.mvvm.utilits.REPOSITORY
import ru.app.mvvm.utilits.TYPE_FIREBASE
import ru.app.mvvm.utilits.TYPE_ROOM
import ru.app.mvvm.utilits.showToast

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }

            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({ onSuccess() }, { showToast(it) })
            }
        }

    }
}
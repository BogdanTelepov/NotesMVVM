package ru.app.mvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.app.mvvm.database.DatabaseRepository
import ru.app.mvvm.models.Note
import ru.app.mvvm.utilits.*

class AppFirebaseRepository : DatabaseRepository {




    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<Note>> = AllNoteLiveData()


    override suspend fun insert(note: Note, onSuccess: () -> Unit) {
        val idNote = REF_DATABASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }


    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        if (AppPreference.getInitUser()) {
            initRefs()
            onSuccess()
        } else {

            AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    initRefs()
                    onSuccess()
                }
                .addOnFailureListener {
                    AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener {
                            initRefs()
                            onSuccess()
                        }
                        .addOnFailureListener { onFail(it.message.toString()) }
                }

        }


    }

    private fun initRefs() {
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }

    override fun signOut() {
        AUTH.signOut()
    }

}
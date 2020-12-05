package ru.app.mvvm.ui.screens.add_new_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.app.R
import ru.app.databinding.FragmentAddNewNoteBinding
import ru.app.mvvm.models.Note
import ru.app.mvvm.utilits.APP_ACTIVITY
import ru.app.mvvm.utilits.showToast


class AddNewNoteFragment : Fragment() {

    private var _binding: FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var viewModel: AddNewNoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(AddNewNoteViewModel::class.java)
        mBinding.btnAddNote.setOnClickListener {
            val name = mBinding.editNoteTitle.toString().trim()
            val text = mBinding.editNoteText.toString().trim()
            if (name.isEmpty()) {
                showToast("Введите имя заметки")
            } else {
                viewModel.insert(Note(name = name, text = text)) {
                    APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
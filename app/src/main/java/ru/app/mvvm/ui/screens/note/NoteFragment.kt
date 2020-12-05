package ru.app.mvvm.ui.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.app.R
import ru.app.databinding.FragmentMainBinding
import ru.app.databinding.FragmentNoteBinding
import ru.app.mvvm.models.Note
import ru.app.mvvm.utilits.APP_ACTIVITY


class NoteFragment : Fragment() {


    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var viewModel: NoteFragmentViewModel
    private lateinit var currentNote: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        currentNote = arguments?.getSerializable("note") as Note
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        init()
    }

    private fun init() {
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
        mBinding.noteName.text = currentNote.name
        mBinding.noteText.text = currentNote.name
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete_note -> {
                viewModel.delete(currentNote) {
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}
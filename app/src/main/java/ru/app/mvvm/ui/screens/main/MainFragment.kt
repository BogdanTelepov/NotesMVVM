package ru.app.mvvm.ui.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.app.R
import ru.app.databinding.FragmentMainBinding
import ru.app.mvvm.models.Note
import ru.app.mvvm.utilits.APP_ACTIVITY
import ru.app.mvvm.utilits.AppPreference


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var _observerList: Observer<List<Note>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        init()
    }

    private fun init() {
        setHasOptionsMenu(true)
        mAdapter = MainAdapter()
        recyclerView = mBinding.recyclerView
        recyclerView.adapter = mAdapter
        _observerList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.allNotes.observe(this, _observerList)

        mBinding.floatingButton.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                viewModel.signOut()
                AppPreference.setInitUser(false)
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)


            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.allNotes.removeObserver(_observerList)
        recyclerView.adapter = null
    }

    companion object {
        fun click(note: Note) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)


        }
    }
}
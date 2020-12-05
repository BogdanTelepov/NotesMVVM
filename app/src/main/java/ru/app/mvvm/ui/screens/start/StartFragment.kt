package ru.app.mvvm.ui.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.app.R
import ru.app.databinding.FragmentStartBinding
import ru.app.mvvm.utilits.*


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var viewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        if (AppPreference.getInitUser()) {
            viewModel.initDatabase(AppPreference.getTypeDB()) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }

        } else {
            initialization()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        mBinding.btnRoom.setOnClickListener {
            viewModel.initDatabase(TYPE_ROOM) {
                AppPreference.setInitUser(true)
                AppPreference.setTypeDB(TYPE_ROOM)
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }

        }

        mBinding.btnFirebase.setOnClickListener {
            mBinding.editEmail.visibility = View.VISIBLE
            mBinding.editPassword.visibility = View.VISIBLE
            mBinding.btnLogin.visibility = View.VISIBLE
            mBinding.btnLogin.setOnClickListener {
                val inputEmail = mBinding.editEmail.text.toString().trim()
                val inputPassword = mBinding.editPassword.text.toString().trim()
                if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    viewModel.initDatabase(TYPE_FIREBASE) {
                        AppPreference.setInitUser(true)
                        AppPreference.setTypeDB(TYPE_FIREBASE)
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }

                } else {
                    showToast("Введите почту и пароль")
                }
            }
        }
    }
}
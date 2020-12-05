package ru.app.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.app.R
import ru.app.databinding.ActivityMainBinding
import ru.app.mvvm.utilits.APP_ACTIVITY
import ru.app.mvvm.utilits.AppPreference

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        AppPreference.getPreference(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
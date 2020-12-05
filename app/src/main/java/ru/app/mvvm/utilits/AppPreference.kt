package ru.app.mvvm.utilits

import android.content.Context
import android.content.SharedPreferences

object AppPreference {
    private const val INIT_USER = "init_user"
    private const val TYPE_DATABASE = "type_database"
    private const val NAME_PREF = "preference"


    private lateinit var preferences: SharedPreferences


    fun getPreference(context: Context): SharedPreferences {
        preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return preferences
    }

    fun setInitUser(init: Boolean) {
        preferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    fun setTypeDB(type: String) {
        preferences.edit()
            .putString(TYPE_DATABASE, type)
            .apply()
    }

    fun getInitUser(): Boolean {
        return preferences.getBoolean(INIT_USER, false)
    }

    fun getTypeDB(): String {
        return preferences.getString(TYPE_DATABASE, TYPE_ROOM).toString()
    }
}
package com.example.smart_lab.backend

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedLib(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

    fun writeToSharedPreferences(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun readFromSharedPreferences(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}
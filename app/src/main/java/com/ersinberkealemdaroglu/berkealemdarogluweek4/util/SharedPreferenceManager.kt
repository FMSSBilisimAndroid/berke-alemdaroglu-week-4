package com.ersinberkealemdaroglu.berkealemdarogluweek4.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager(context: Context) {

    private var sharedPref: SharedPreferences =
        context.getSharedPreferences("sharedPreferenceUtils", Context.MODE_PRIVATE)

    companion object {
        const val CHECK_STARTED = "com.ersinberkealemdaroglu.STARTED"
    }

    private var bool = true

    fun setBool(key: Boolean) {
        bool = key
        sharedPref.edit().putBoolean(CHECK_STARTED, bool).apply()
    }

    fun getBool(): Boolean {
        return sharedPref.getBoolean(CHECK_STARTED, bool)
    }

}
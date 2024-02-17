package com.devhouse.dearmyfriends.mng

import android.content.Context
import android.content.SharedPreferences
import com.devhouse.dearmyfriends.base.BaseActivity

class SharedManager {
    private var activity: BaseActivity
    private var pref: SharedPreferences

    constructor(activity: BaseActivity) {
        this.activity = activity
        this.pref = this.activity.getPreferences(Context.MODE_PRIVATE)
    }

    /* about preference */
    fun setString(key: SharedKey, value: String) {
        val editor = pref.edit()
        editor.putString(key.key, value)
        editor.apply()
    }

    fun getString(key: SharedKey): String {
        var def = ""

        if(pref.getString(key.key, "") != null) {
            def = pref.getString(key.key, "")!!
        }

        return def
    }
}

enum class SharedKey(var key: String) {
    NONE("")
}
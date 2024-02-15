package com.devhouse.dearmyfriends.mng

import android.util.Log

class LogManager {

    companion object {
        val instance = LogManager()
    }

    fun consoleLog(type: LogType, msg: String) {
        Log.i(type.title, msg)
    }
}
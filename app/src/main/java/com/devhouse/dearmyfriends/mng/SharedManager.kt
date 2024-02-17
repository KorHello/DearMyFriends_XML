package com.devhouse.dearmyfriends.mng

class SharedManager {
    companion object {
        val instance = SharedManager()
    }


}

enum class SharedKey(var key: String) {
    NONE("")
}
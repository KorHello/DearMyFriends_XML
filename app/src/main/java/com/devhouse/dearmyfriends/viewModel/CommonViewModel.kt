package com.devhouse.dearmyfriends.viewModel

import com.devhouse.dearmyfriends.item.RequestInfo
import com.devhouse.dearmyfriends.mng.CallManager
import com.devhouse.dearmyfriends.mng.PathType
import com.google.gson.JsonObject

class CommonViewModel {

    companion object {
        val instance = CommonViewModel()
    }

    init {

    }

    fun callVersionInfo() {
        val param: JsonObject = JsonObject()
        param.addProperty("osType", "A")
        param.addProperty("fcmKey", "And Fcm Test")
        param.addProperty("deviceId", "And Test")

        val reqInfo = RequestInfo()
        reqInfo.setData(PathType.VERSION_CHECK, param)

        CallManager(reqInfo).postCall()
    }
}
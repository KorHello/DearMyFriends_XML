package com.devhouse.dearmyfriends.viewModel

import com.devhouse.dearmyfriends.item.RequestInfo
import com.devhouse.dearmyfriends.item.ResInfo
import com.devhouse.dearmyfriends.item.VersionInfo
import com.devhouse.dearmyfriends.mng.CallManager
import com.devhouse.dearmyfriends.mng.LogManager
import com.devhouse.dearmyfriends.mng.LogType
import com.devhouse.dearmyfriends.mng.PathType
import com.devhouse.dearmyfriends.mng.ResAction
import com.google.gson.Gson
import com.google.gson.JsonObject

class CommonViewModel: ResAction {

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

        CallManager(reqInfo).postCall(this)
    }

    /* ResAction */
    override fun successAct(path: PathType, resInfo: ResInfo) {
        if(path == PathType.VERSION_CHECK) {
            // 데이터 파싱 테스트
            val data = Gson().fromJson<VersionInfo>(resInfo.bodyDic.toString(), VersionInfo::class.java)
            LogManager.instance.consoleLog(LogType.CHECK_ITEMDATA, data.updateNum)
            LogManager.instance.consoleLog(LogType.CHECK_ITEMDATA, data.versionName)
            LogManager.instance.consoleLog(LogType.CHECK_ITEMDATA, data.updateType)
            LogManager.instance.consoleLog(LogType.CHECK_ITEMDATA, data.showNote)
        }
    }

    override fun failAct(resInfo: ResInfo) {

    }
}
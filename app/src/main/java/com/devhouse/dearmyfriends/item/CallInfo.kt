package com.devhouse.dearmyfriends.item

import com.devhouse.dearmyfriends.base.DefineManager
import com.devhouse.dearmyfriends.mng.LogManager
import com.devhouse.dearmyfriends.mng.LogType
import com.devhouse.dearmyfriends.mng.PathType
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject

data class RequestInfo (
    var url: String = "",
    var path: PathType = PathType.NONE,
    var param: JsonObject = JsonObject()
) {
    fun setData(path: PathType, param: JsonObject?) {
        this.path = path
        this.url = DefineManager.apiMode.value + path.pathString

        LogManager.instance.consoleLog(LogType.CHECK_REQINFO, this.path.pathString)
        LogManager.instance.consoleLog(LogType.CHECK_REQINFO, this.url)

        if(param != null) {
            this.param = param
            LogManager.instance.consoleLog(LogType.CHECK_REQINFO, this.param.toString())
        }
    }
}

data class ResInfo (
    var resCode: String = "",
    var resMsg: String = "",
    var bodyType: String = "",

    var bodyDic: JsonObject? = null,
    var bodyArray: JsonArray? = null
) {
    fun parseData(body: String) {
        val jsonObj = Gson().fromJson<JsonObject>(body, JsonObject::class.java)

        this.resCode = jsonObj.get("code").asString
        this.resMsg = jsonObj.get("msg").asString

        val bType = jsonObj.get("")

        LogManager.instance.consoleLog(LogType.CHECK_RESINFO, this.resCode)
        LogManager.instance.consoleLog(LogType.CHECK_RESINFO, this.resMsg)
    }
}

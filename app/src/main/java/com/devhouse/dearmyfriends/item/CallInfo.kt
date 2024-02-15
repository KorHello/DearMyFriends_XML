package com.devhouse.dearmyfriends.item

import com.devhouse.dearmyfriends.base.DefineManager
import com.devhouse.dearmyfriends.mng.LogManager
import com.devhouse.dearmyfriends.mng.LogType
import com.devhouse.dearmyfriends.mng.PathType
import com.google.gson.JsonObject

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
    var body: Any?
) {

}

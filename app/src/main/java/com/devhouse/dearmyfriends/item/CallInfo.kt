package com.devhouse.dearmyfriends.item

import com.devhouse.dearmyfriends.base.DefineManager
import com.devhouse.dearmyfriends.mng.BodyType
import com.devhouse.dearmyfriends.mng.LogManager
import com.devhouse.dearmyfriends.mng.LogType
import com.devhouse.dearmyfriends.mng.PathType
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import java.lang.Exception

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
    var bodyType: BodyType = BodyType.NONE,

    var bodyDic: JsonObject = JsonObject(),
    var bodyArray: JsonArray = JsonArray()
) {
    fun getErrorInfo() {
        this.resCode = "9999"
        this.resMsg = "통신 중 오류가 발생하였습니다, 잠시 후 다시 시도해주세요."
        this.bodyType = BodyType.NONE
    }

    fun parseData(body: String) {
        val jsonObj = Gson().fromJson<JsonObject>(body, JsonObject::class.java)

        try {
            this.resCode = jsonObj.get("code").asString
            this.resMsg = jsonObj.get("msg").asString

            val bType = jsonObj.get("type").asString
            if (bType == "M" || bType == "L") {
                if (bType == "M") {
                    this.bodyType = BodyType.MAP
                    this.bodyDic = jsonObj.get("body").asJsonObject
                } else {
                    this.bodyType = BodyType.LIST
                    this.bodyArray = jsonObj.get("body").asJsonArray
                }
            } else {
                this.bodyType = BodyType.NONE
            }
        } catch (e: Exception) {
            this.resCode = "9990"
            this.resMsg = "통신 중 오류가 발생하였습니다. 잠시 후 다시 시도해주세요."
            this.bodyType = BodyType.NONE
            this.bodyDic = JsonObject()
            this.bodyArray = JsonArray()
        }
    }
}

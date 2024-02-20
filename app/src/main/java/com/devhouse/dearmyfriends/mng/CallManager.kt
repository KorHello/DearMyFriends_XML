package com.devhouse.dearmyfriends.mng

import com.devhouse.dearmyfriends.item.DeviceInfo
import com.devhouse.dearmyfriends.item.RequestInfo
import com.devhouse.dearmyfriends.item.ResInfo
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class CallManager(req: RequestInfo) {
    private val jsonType = "application/json".toMediaType()
    var reqInfo: RequestInfo

    init {
        this.reqInfo = req
    }

    fun postCall(resAction: ResAction) {
        val deviceInfo: DeviceInfo = DeviceInfo()
        deviceInfo.getDeviceInfo(GetDeviceInfoType.API_HEADER)

        val apiUrl = reqInfo.url
        val requestInfo = Request.Builder().url(apiUrl)
            .post(RequestBody.create(jsonType, reqInfo.param.toString()))
            .addHeader("uuid", deviceInfo.uuid)
            .addHeader("osType", "A")
            .addHeader("contentInfo", "modelName: "+ deviceInfo.modelName + ", osVersion: " + deviceInfo.osVer)
            .build()

        val client = OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .build()

        client.newCall(requestInfo).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                LogManager.instance.consoleLog(LogType.CHECK_HTTPINFO, response.code.toString())

                val resInfo = ResInfo()
                response.body?.let {
                    resInfo.parseData(it.string())
                    resAction.successAct(reqInfo.path, resInfo)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                LogManager.instance.consoleLog(LogType.CHECK_HTTPERROR, e.toString())

                val failInfo = ResInfo()
                failInfo.getErrorInfo()
                resAction.failAct(reqInfo.path, failInfo)
            }
        })
    }
}

interface ResAction {
    fun successAct(path: PathType, resInfo: ResInfo)
    fun failAct(path: PathType, resInfo: ResInfo)
}
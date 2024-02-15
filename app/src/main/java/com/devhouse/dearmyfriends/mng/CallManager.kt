package com.devhouse.dearmyfriends.mng

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

class CallManager(req: RequestInfo) {
    private val jsonType = "application/json".toMediaType()
    var reqInfo: RequestInfo

    init {
        this.reqInfo = req
    }

    fun postCall() {
        val apiUrl = reqInfo.url
        val requestInfo = Request.Builder().url(apiUrl)
            .post(RequestBody.create(jsonType, reqInfo.param.toString()))
            .addHeader("uuid", "AOSTest")
            .addHeader("osType", "I")
            .addHeader("contentInfo", "call Test")
            .build()

        val client = OkHttpClient()
        client.newCall(requestInfo).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                LogManager.instance.consoleLog(LogType.CHECK_HTTPINFO, response.code.toString())
                response.body?.let { LogManager.instance.consoleLog(LogType.CHECK_HTTPINFO, it.string()) }
            }

            override fun onFailure(call: Call, e: IOException) {
                LogManager.instance.consoleLog(LogType.CHECK_HTTPERROR, e.toString())
            }
        })
    }
}

interface ResAction {
    fun successAct(path: PathType, resInfo: ResInfo)
    fun failAct(resInfo: ResInfo)
}
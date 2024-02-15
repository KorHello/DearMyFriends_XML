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
                response.body?.let {
                    // 널이 아니면 여길 탐,
                    // Swift 의 경우엔 if let obj = null 이 될만한 오브젝트 인데, 이걸 이렇게 태우는듯 함
                    // 이미,. 코틀린은 람다가 거의 생활화가 되어 있는듯 함
                    // OkHttp 사용시, response.body.string() 을 두번 호출시 오류나니 주의, Log 를 찍을거면 해당 객체에 들어가서 찍어보기 ( ResInfo 완성되어 있음, 여기 안찍히면 이대로 안내려오는것임. )
                    val resInfo = ResInfo()
                    resInfo.parseData(it.string())
                }
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
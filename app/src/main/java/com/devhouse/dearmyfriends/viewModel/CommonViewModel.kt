package com.devhouse.dearmyfriends.viewModel

import com.devhouse.dearmyfriends.IntroActivity
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.DeviceInfo
import com.devhouse.dearmyfriends.item.MsgPopInfo
import com.devhouse.dearmyfriends.item.RequestInfo
import com.devhouse.dearmyfriends.item.ResInfo
import com.devhouse.dearmyfriends.item.VersionInfo
import com.devhouse.dearmyfriends.mng.CallManager
import com.devhouse.dearmyfriends.mng.GetDeviceInfoType
import com.devhouse.dearmyfriends.mng.PathType
import com.devhouse.dearmyfriends.mng.ResAction
import com.devhouse.dearmyfriends.mng.ToolManager
import com.google.gson.Gson
import com.google.gson.JsonObject

class CommonViewModel: ResAction {

    var cmmMsgBox: MsgPopInfo = MsgPopInfo()
    var testString:String = ""

    lateinit var introV: IntroActivity

    companion object {
        val instance = CommonViewModel()
    }

    constructor() {

    }
    constructor(intro: IntroActivity) {
        this.introV = intro
    }

    fun callVersionInfo(baseActivity: BaseActivity) {
        val deviceInfo = DeviceInfo()
        deviceInfo.getDeviceInfo(GetDeviceInfoType.CALL_INTRO_API)

        val param: JsonObject = deviceInfo.makeParam()
        val reqInfo = RequestInfo()
        reqInfo.setData(PathType.VERSION_CHECK, param)

        CallManager(reqInfo).postCall(this)
    }

    /* ResAction */
    override fun successAct(path: PathType, resInfo: ResInfo) {
        if(path == PathType.VERSION_CHECK) {
            // 데이터 파싱 테스트
            val data = Gson().fromJson<VersionInfo>(resInfo.bodyDic.toString(), VersionInfo::class.java)
            if(introV != null) {
                introV.updateAction(data.checkUpdate(), data)
            }
        }
    }

    override fun failAct(resInfo: ResInfo) {
    }
}
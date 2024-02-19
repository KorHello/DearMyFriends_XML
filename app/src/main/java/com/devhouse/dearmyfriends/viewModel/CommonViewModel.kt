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
import com.devhouse.dearmyfriends.views.setting.CheckVersionView
import com.devhouse.dearmyfriends.views.setting.PushSettingView
import com.google.gson.Gson
import com.google.gson.JsonObject

class CommonViewModel: ResAction {

    var cmmMsgBox: MsgPopInfo = MsgPopInfo()
    var testString:String = ""

    var introV: IntroActivity? = null
    var checkVersionV: CheckVersionView? = null
    var pushAlarmV: PushSettingView? = null

    constructor(intro: IntroActivity) {
        this.introV = intro
    }

    constructor(checkVersion: CheckVersionView) {
        this.checkVersionV = checkVersion
    }

    constructor(pushAlarm: PushSettingView) {
        this.pushAlarmV = pushAlarm
    }

    fun callVersionInfo() {
        val deviceInfo = DeviceInfo()
        deviceInfo.getDeviceInfo(GetDeviceInfoType.CALL_INTRO_API)

        val param: JsonObject = deviceInfo.makeParam()
        val reqInfo = RequestInfo()
        reqInfo.setData(PathType.VERSION_CHECK, param)

        CallManager(reqInfo).postCall(this)
    }

    fun callPushState() {
        val deviceInfo = DeviceInfo()
        deviceInfo.getDeviceInfo(GetDeviceInfoType.CALL_INTRO_API)

        val param: JsonObject = deviceInfo.makeParamGetPushState()
        val reqInfo = RequestInfo()
        reqInfo.setData(PathType.GET_PUSH_STATE, param)

        CallManager(reqInfo).postCall(this)
    }

    /* ResAction */
    override fun successAct(path: PathType, resInfo: ResInfo) {
        if(path == PathType.VERSION_CHECK) {
            // 데이터 파싱 테스트
            val data = Gson().fromJson<VersionInfo>(resInfo.bodyDic.toString(), VersionInfo::class.java)
            if(introV != null) {
                this.introV?.let { introActivity: IntroActivity ->
                    introActivity.updateAction(data.checkUpdate(), data)
                }
            } else if (checkVersionV != null) {
                this.checkVersionV?.let { checkVersionView: CheckVersionView ->
                    checkVersionView.showUpdateInfo(data)
                }
            }
        } else if (path == PathType.GET_PUSH_STATE) {
            var status = false

            val pushState = resInfo.bodyDic.get("pushOnOff").asString
            if("Y".equals(pushState)) {
                status = true
            }

            this.pushAlarmV?.let { pushSettingView: PushSettingView ->
                pushSettingView.switchStateChange(status)
            }
        }
    }

    override fun failAct(path: PathType, resInfo: ResInfo) {
        if(path == PathType.VERSION_CHECK) {
            
        }
    }
}
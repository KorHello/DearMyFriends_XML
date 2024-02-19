package com.devhouse.dearmyfriends.viewModel

import com.devhouse.dearmyfriends.IntroActivity
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.AppNotice
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
import com.devhouse.dearmyfriends.views.setting.AppNoticeListView
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
    var noticeListV: AppNoticeListView? = null

    private var pushState: Boolean = false

    constructor(intro: IntroActivity) {
        this.introV = intro
    }

    constructor(checkVersion: CheckVersionView) {
        this.checkVersionV = checkVersion
    }

    constructor(pushAlarm: PushSettingView) {
        this.pushAlarmV = pushAlarm
    }

    constructor(noticeV: AppNoticeListView) {
        this.noticeListV = noticeV
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

    fun callChangePushState(currentState: Boolean) {
        this.pushState = currentState

        var pushStateStr = "Y"
        if(!this.pushState) {
            pushStateStr = "N"
        }

        val deviceInfo = DeviceInfo()
        deviceInfo.getDeviceInfo(GetDeviceInfoType.CALL_INTRO_API)

        val param: JsonObject = deviceInfo.makeParamChangePushState(pushStateStr)
        val reqInfo = RequestInfo()
        reqInfo.setData(PathType.UPDATE_PUSH_STATE, param)

        CallManager(reqInfo).postCall(this)
    }

    fun callGetAppNotices() {
        val deviceInfo = DeviceInfo()
        deviceInfo.getDeviceInfo(GetDeviceInfoType.CALL_INTRO_API)

        val reqInfo = RequestInfo()
        reqInfo.setData(PathType.GET_APP_NOTICE, null)

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
        } else if (path == PathType.UPDATE_PUSH_STATE) {
            this.pushAlarmV?.let { pushSettingView: PushSettingView ->
                pushSettingView.switchStateChange(this.pushState)
            }
        } else if (path == PathType.GET_APP_NOTICE) {
            var notiLists: ArrayList<AppNotice> = ArrayList<AppNotice>()
            if(resInfo.bodyArray.size() > 0) {
                for(item in resInfo.bodyArray) {
                    val jsonItem = item.asJsonObject
                    val data = Gson().fromJson<AppNotice>(jsonItem.toString(), AppNotice::class.java)
                    notiLists.add(data)
                }
            }

            noticeListV?.let { appNoticeListView: AppNoticeListView ->
                appNoticeListView.loadNotices(notiLists)
            }
        }
    }

    override fun failAct(path: PathType, resInfo: ResInfo) {
        if(path == PathType.VERSION_CHECK) {
            
        }
    }
}
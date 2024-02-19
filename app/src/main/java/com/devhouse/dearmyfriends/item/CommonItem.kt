package com.devhouse.dearmyfriends.item

import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.base.BaseApplication
import com.devhouse.dearmyfriends.mng.GetDeviceInfoType
import com.devhouse.dearmyfriends.mng.SharedKey
import com.devhouse.dearmyfriends.mng.SharedManager
import com.google.gson.JsonObject
import java.util.UUID

data class VersionInfo (
    var updateNum: String = "",
    var versionName: String = "",
    var updateType: String = "",
    var showNote: String = ""
) {
    fun checkUpdate(): Boolean {
        val appInfo = DeviceInfo()
        appInfo.getDeviceInfo(GetDeviceInfoType.ONLY_VERSION_CHECK)

        var updateChk = false
        val updateNumInt = updateNum.toInt()
        if(updateNumInt > appInfo.appBuildNum) {
            updateChk = true
        }

        return updateChk
    }
}

data class DeviceInfo (
    var uuid: String = "",
    var fcmKey: String = "",
    var osVer: String = "",
    var modelName: String = "",
    var appVersion: String = "",
    var appBuildNum: Int = 0
) {
    fun getDeviceInfo( type: GetDeviceInfoType) {
        if (type == GetDeviceInfoType.ONLY_VERSION_CHECK) {
            val appContext = BaseApplication().getAppContext()
            val packageInfo = appContext.packageManager.getPackageInfo("com.devhouse.dearmyfriends", 0)
            this.appVersion = packageInfo.versionName
            this.appBuildNum = packageInfo.versionCode
        } else {
            this.fcmKey = "test"
            this.osVer = android.os.Build.VERSION.SDK_INT.toString()
            this.modelName = android.os.Build.MODEL
            this.uuid = getUUIDString()

            if(type == GetDeviceInfoType.VERSION_CHECK || type == GetDeviceInfoType.ALL) {
                val appContext = BaseApplication().getAppContext()
                val packageInfo = appContext.packageManager.getPackageInfo("com.devhouse.dearmyfriends", 0)
                this.appVersion = packageInfo.versionName
                this.appBuildNum = packageInfo.versionCode
            }
        }
    }

    fun getUUIDString(): String {
        var uuidStr: String = ""

        val sharedString = SharedManager.instance.getString(SharedKey.GET_DEVICEINFO_UUID)
        if("".equals(sharedString)) {
            uuidStr = UUID.randomUUID().toString()

            // UUID 저장
            SharedManager.instance.setString(SharedKey.GET_DEVICEINFO_UUID, uuidStr)
        } else {
            uuidStr = sharedString
        }

        return uuidStr
    }

    fun makeParam(): JsonObject {
        val json = JsonObject()
        json.addProperty("osType", "A")
        json.addProperty("fcmKey", this.fcmKey)
        json.addProperty("deviceId", this.uuid)

        return json
    }

    fun makeParamGetPushState(): JsonObject {
        val json = JsonObject()
        json.addProperty("deviceId", this.uuid)
        return json
    }

    fun makeParamChangePushState(pushState: String): JsonObject {
        val json = JsonObject()
        json.addProperty("deviceId", this.uuid)
        json.addProperty("pushOnOff", pushState)
        return json
    }
}
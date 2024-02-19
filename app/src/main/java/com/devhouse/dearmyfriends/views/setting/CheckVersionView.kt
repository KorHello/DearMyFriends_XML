package com.devhouse.dearmyfriends.views.setting

import android.os.Handler
import android.widget.Button
import android.widget.TextView
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.DeviceInfo
import com.devhouse.dearmyfriends.item.VersionInfo
import com.devhouse.dearmyfriends.mng.GetDeviceInfoType
import com.devhouse.dearmyfriends.viewModel.CommonViewModel

class CheckVersionView: BaseActivity(R.layout.activity_check_version) {

    private lateinit var checkVersionLabel: TextView
    private lateinit var currentVersionNameLabel: TextView

    private lateinit var backBtn: Button

    override fun initView() {
        super.initView()

        backBtn = findViewById(R.id.check_version_back)
        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun initModel() {
        super.initModel()

        val cmmVM = CommonViewModel(this)
        cmmVM.callVersionInfo()
    }

    override fun initAction() {
        super.initAction()
    }

    /* 통신 후 액션 */
    fun showUpdateInfo(versionInfo: VersionInfo) {
        val infoHandle = Handler()
        infoHandle.post {
            val currentInfo = DeviceInfo()
            currentInfo.getDeviceInfo(GetDeviceInfoType.VERSION_CHECK)

            // 앱 빌드 넘버 비교함
            val newBuildNum = versionInfo.updateNum.toInt()
            if(newBuildNum > currentInfo.appBuildNum) {
                currentVersionNameLabel.text = currentInfo.appVersion
                checkVersionLabel.text = "최신버전으로 업데이트가 필요합니다."
            } else {
                currentVersionNameLabel.text = currentInfo.appVersion
                checkVersionLabel.text = "이미 최신버전 입니다."
            }
        }
    }
}
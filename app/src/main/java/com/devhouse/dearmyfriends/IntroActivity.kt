package com.devhouse.dearmyfriends

import android.content.Intent
import android.os.Handler
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.MsgPopInfo
import com.devhouse.dearmyfriends.item.VersionInfo
import com.devhouse.dearmyfriends.mng.PopType
import com.devhouse.dearmyfriends.viewModel.CommonViewModel

class IntroActivity: BaseActivity(R.layout.activity_intro) {

    override fun initModel() {
        super.initModel()
    }

    override fun initAction() {
        super.initAction()

        this.lottieLoadingV = findViewById(R.id.lottie_loading_view) // 로티 인잇
        this.loadingBarAction(true)

        val cmmVM = CommonViewModel(this)
        cmmVM.callVersionInfo()
    }

    fun updateAction(state: Boolean, info: VersionInfo?) {
        Handler(mainLooper).post {
            this.loadingBarAction(false)

            if(state) {
                val info = MsgPopInfo()
                info.type = PopType.MSG
                info.title = "알림"
                info.content = "스토어에 최신 업데이트가 있습니다. 업데이트 후 다시 접속해주세요."
                this.showExitPop(info)
            } else {
                Thread.sleep(1000)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
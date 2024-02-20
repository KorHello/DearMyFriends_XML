package com.devhouse.dearmyfriends

import android.content.Intent
import android.os.Handler
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.VersionInfo
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

            } else {
                Thread.sleep(1000)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
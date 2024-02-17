package com.devhouse.dearmyfriends

import android.content.Intent
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.VersionInfo
import com.devhouse.dearmyfriends.viewModel.CommonViewModel

class IntroActivity: BaseActivity(R.layout.activity_intro) {

    override fun initModel() {
        super.initModel()
    }

    override fun initAction() {
        super.initAction()

        var cmmVM = CommonViewModel()
        cmmVM.callVersionInfo(this)
    }

    fun updateAction(state: Boolean, info: VersionInfo?) {
        if(state) {

        } else {
            Thread.sleep(2000)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
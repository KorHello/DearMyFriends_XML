package com.devhouse.dearmyfriends.views.setting

import android.widget.Button
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.base.BaseActivity

class PushSettingView: BaseActivity(R.layout.activity_setting_push) {

    private lateinit var backBtn: Button
    override fun initView() {
        super.initView()

        backBtn = findViewById(R.id.push_set_back)
        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun initModel() {
        super.initModel()
    }

    override fun initAction() {
        super.initAction()
    }
}
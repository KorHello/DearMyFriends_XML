package com.devhouse.dearmyfriends.views.setting

import android.widget.Button
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.base.BaseActivity

class CheckVersionView: BaseActivity(R.layout.activity_check_version) {

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
    }

    override fun initAction() {
        super.initAction()
    }
}
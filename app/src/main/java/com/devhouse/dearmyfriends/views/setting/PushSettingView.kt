package com.devhouse.dearmyfriends.views.setting

import android.os.Handler
import android.widget.Button
import android.widget.Switch
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.base.BaseActivity

class PushSettingView: BaseActivity(R.layout.activity_setting_push) {

    private lateinit var switchBtn: Switch
    private lateinit var backBtn: Button
    override fun initView() {
        super.initView()

        switchBtn = findViewById(R.id.all_push_state)

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

    /* switch State change */
    fun switchStateChange(state: Boolean) {
        Handler(mainLooper).post {
            this.switchBtn.isChecked = state
        }
    }
}
package com.devhouse.dearmyfriends.views.setting

import android.os.Handler
import android.widget.Button
import android.widget.Switch
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.viewModel.CommonViewModel

class PushSettingView: BaseActivity(R.layout.activity_setting_push) {

    private lateinit var switchBtn: Switch
    private lateinit var backBtn: Button

    lateinit var commonVM: CommonViewModel
    private var allState: Boolean = false

    private var isInitAction: Boolean = true

    override fun initView() {
        super.initView()

        switchBtn = findViewById(R.id.all_push_state)
        switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!this.isInitAction) {
                this.loadingBarAction(true)
                this.commonVM.callChangePushState(isChecked)
            }
        }

        backBtn = findViewById(R.id.push_set_back)
        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun initModel() {
        super.initModel()

        this.lottieLoadingV = findViewById(R.id.lottie_loading_view) // 로티 인잇
        this.loadingBarAction(true)

        commonVM = CommonViewModel(this)
        commonVM.callPushState()
    }

    override fun initAction() {
        super.initAction()
    }

    /* switch State change */
    fun switchStateChange(state: Boolean) {
        this.allState = state

        Handler(mainLooper).post {
            this.loadingBarAction(false)

            this.switchBtn.isChecked = state
            this.isInitAction = false
        }
    }
}
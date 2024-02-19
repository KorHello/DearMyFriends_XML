package com.devhouse.dearmyfriends.views.setting

import android.content.Intent
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.views.holder.SettingMainAdapter

class SettingMainListView: BaseActivity(R.layout.activity_setting_main) {

    lateinit var backBtn: Button
    lateinit var mainList: RecyclerView

    override fun initView() {
        super.initView()

        backBtn = findViewById(R.id.setting_main_back)
        mainList = findViewById(R.id.setting_list)

        backBtn.setOnClickListener {
            finish()
        }

        val settingMenuAdt = SettingMainAdapter(this)
        mainList.adapter = settingMenuAdt
        mainList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun initModel() {
        super.initModel()

    }

    override fun initAction() {
        super.initAction()
    }

    fun moveView(moveInt: Int) {
        var intent: Intent? = null

        if(moveInt == 0) {
            intent = Intent(this, AppNoticeListView::class.java)
        } else if (moveInt == 1) {
            intent = Intent(this, PushSettingView::class.java)
        } else if (moveInt == 2) {
            intent = Intent(this, CheckVersionView::class.java)
        }

        intent?.let { intent: Intent ->
            startActivity(intent)
        }
    }
}
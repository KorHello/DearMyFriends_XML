package com.devhouse.dearmyfriends.views.setting

import android.widget.Button
import android.widget.TextView
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.AppNotice

class AppNoticeDetailView: BaseActivity(R.layout.activity_notice_detail) {

    private lateinit var backBtn: Button
    private lateinit var appNotiTitleLabel: TextView
    private lateinit var appNotiDateLabel: TextView
    private lateinit var appNotiContentLabel: TextView

    private var appNotice: AppNotice = AppNotice()

    override fun initView() {
        super.initView()

        backBtn = findViewById(R.id.app_notice_detail_back)
        appNotiTitleLabel = findViewById(R.id.app_notice_detail_title)
        appNotiDateLabel = findViewById(R.id.app_notice_detail_date)
        appNotiContentLabel = findViewById(R.id.app_notice_detail_content)

        backBtn.setOnClickListener {
            finish()
        }

        val intent = intent
        val appNotiTitle = intent.getStringExtra("appNoticeTitle")
        val appNotiDate = intent.getStringExtra("appNoticeDate")
        val appNotiContent = intent.getStringExtra("appNoticeContent")

        appNotiTitle?.let { notiTitle ->
            appNotice.title = notiTitle
        }

        appNotiDate?.let { notiDate ->
            appNotice.regDate = notiDate
        }

        appNotiContent?.let { notiContent ->
            appNotice.content = notiContent
        }

        appNotiTitleLabel.text = appNotice.title
        appNotiDateLabel.text = appNotice.regDate
        appNotiContentLabel.text = appNotice.content
    }

    override fun initModel() {
        super.initModel()
    }

    override fun initAction() {
        super.initAction()
    }

}
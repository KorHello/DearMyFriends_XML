package com.devhouse.dearmyfriends.views.setting

import android.content.Intent
import android.os.Handler
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.AppNotice
import com.devhouse.dearmyfriends.viewModel.CommonViewModel
import com.devhouse.dearmyfriends.views.holder.AppNoticeAdapter

class AppNoticeListView: BaseActivity(R.layout.activity_app_notice) {

    private lateinit var notiList: RecyclerView
    private lateinit var backBtn: Button
    override fun initView() {
        super.initView()

        notiList = findViewById(R.id.app_notice_list)
        backBtn = findViewById(R.id.app_notice_back)
        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun initModel() {
        super.initModel()

        this.lottieLoadingV = findViewById(R.id.lottie_loading_view) // 로티 인잇
        this.loadingBarAction(true)

        val commonVM = CommonViewModel(this)
        commonVM.callGetAppNotices()
    }

    override fun initAction() {
        super.initAction()
    }

    fun loadNotices(appNotices: ArrayList<AppNotice>) {
        Handler(mainLooper).post {
            this.loadingBarAction(false)

            val adapter = AppNoticeAdapter(this, appNotices)
            notiList.adapter = adapter
            notiList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }

    fun moveView(info: AppNotice) {
        val intent = Intent(this, AppNoticeDetailView::class.java)
        intent.putExtra("appNoticeTitle", info.title)
        intent.putExtra("appNoticeDate", info.regDate)
        intent.putExtra("appNoticeContent", info.content)

        startActivity(intent)
    }
}
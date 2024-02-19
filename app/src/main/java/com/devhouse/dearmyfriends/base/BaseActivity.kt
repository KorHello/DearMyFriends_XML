package com.devhouse.dearmyfriends.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.devhouse.dearmyfriends.item.MsgPopInfo
import com.devhouse.dearmyfriends.mng.PopType

open class BaseActivity(layout: Int): ComponentActivity() {

    var isFirst: Boolean = true
    var layoutId: Int
    init {
        this.layoutId = layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        this.initView()
        this.initModel()
    }

    override fun onResume() {
        super.onResume()

        if(isFirst) {
            isFirst = false
            this.initAction()
        } else {
            this.reloadAction()
        }
    }

    open fun initView() {

    }

    open fun initModel() {

    }

    open fun initAction() {

    }

    open fun reloadAction() {

    }

    open fun showMsgPop(info: MsgPopInfo) {
        if(info.type == PopType.MSG) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle(info.title).setMessage(info.content)
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, id ->

                })
            alert.show()
        } else if (info.type == PopType.TOAST) {
            Toast.makeText(this, info.content, Toast.LENGTH_SHORT).show()
        }
    }
}
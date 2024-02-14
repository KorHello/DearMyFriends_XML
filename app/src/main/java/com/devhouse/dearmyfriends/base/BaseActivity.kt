package com.devhouse.dearmyfriends.base

import android.os.Bundle
import androidx.activity.ComponentActivity

open class BaseActivity(layout: Int): ComponentActivity() {

    var isFirst: Boolean = true
    var layoutId: Int
    init {
        this.layoutId = layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

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

    open fun initModel() {

    }

    open fun initAction() {

    }

    open fun reloadAction() {

    }
}
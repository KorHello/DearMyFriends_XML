package com.devhouse.dearmyfriends.viewModel

import com.devhouse.dearmyfriends.item.ResInfo
import com.devhouse.dearmyfriends.mng.PathType
import com.devhouse.dearmyfriends.mng.ResAction

class SentenceViewModel: ResAction {

    companion object {
        val instance = SentenceViewModel()
    }

    fun getSetences() {
        
    }

    /* Http Delegate */
    override fun successAct(path: PathType, resInfo: ResInfo) {

    }

    override fun failAct(path: PathType, resInfo: ResInfo) {

    }
}
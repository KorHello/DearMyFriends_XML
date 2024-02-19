package com.devhouse.dearmyfriends.viewModel

import com.devhouse.dearmyfriends.MainActivity
import com.devhouse.dearmyfriends.item.RequestInfo
import com.devhouse.dearmyfriends.item.ResInfo
import com.devhouse.dearmyfriends.item.Sentence
import com.devhouse.dearmyfriends.mng.CallManager
import com.devhouse.dearmyfriends.mng.LogManager
import com.devhouse.dearmyfriends.mng.LogType
import com.devhouse.dearmyfriends.mng.PathType
import com.devhouse.dearmyfriends.mng.ResAction
import com.google.gson.Gson

class SentenceViewModel: ResAction {

    var mainActivity: MainActivity

    constructor(activity: MainActivity) {
        this.mainActivity = activity
    }

    fun getSetences() {
        val reqInfo = RequestInfo()
        reqInfo.setData(PathType.GET_SENTENCE, null)
        CallManager(reqInfo).postCall(this)
    }

    /* Http Delegate */
    override fun successAct(path: PathType, resInfo: ResInfo) {
        if (path == PathType.GET_SENTENCE) {
            var sentenceArray = ArrayList<Sentence>()
            var gson = Gson()

            for(item in resInfo.bodyArray) {
                val itemJSON = item.asJsonObject
                val sentenceInfo = gson.fromJson<Sentence>(itemJSON, Sentence::class.java)

                sentenceArray.add(sentenceInfo)
            }

            mainActivity.showSentence(sentenceArray)
        }
    }

    override fun failAct(path: PathType, resInfo: ResInfo) {

    }
}
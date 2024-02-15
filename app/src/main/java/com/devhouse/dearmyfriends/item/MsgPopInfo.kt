package com.devhouse.dearmyfriends.item

import com.devhouse.dearmyfriends.mng.PopType

data class MsgPopInfo (
    var type: PopType = PopType.MSG,
    var title: String = "",
    var content: String = ""
) {

}

data class PopBtnInfo (
    var title: String = "",
    var action: Void? = null
)
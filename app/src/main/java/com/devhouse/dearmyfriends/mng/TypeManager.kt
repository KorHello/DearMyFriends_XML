package com.devhouse.dearmyfriends.mng

enum class ApiType(var value: String) {
    TEST("http://app.devhouse.store:16502"),
    REAL("https://app.devhouse.store:16501")
}

enum class PathType(var pathString: String) {
    NONE(""),
    VERSION_CHECK("/cmm/getVersion.dmfap")
}

enum class BodyType {
    NONE, MAP, LIST
}

enum class LogType(var title: String) {
    NONE(""),
    CHECK_REQINFO("reqInfo_"),
    CHECK_HTTPINFO("resInfo_"),
    CHECK_HTTPERROR("CallError_"),
    CHECK_RESINFO("resParseInfo_")
}
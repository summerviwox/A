package com.summer.a.album.bean

data class Picture(
    var id:Int,
    var locpath:String,
    var netpath:String,
    var ctime:Long,
    var utime:Long,
    var atype:String,
    var duration:Long,
    var name:String,
    var ctype:String,
    var remark:String,
    var parentid:String,
    var enable:Int,
)

data class ListData<T>(var data:ArrayList<T>)

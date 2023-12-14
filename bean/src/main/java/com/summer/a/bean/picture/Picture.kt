package com.summer.a.bean.picture

data class Picture(
    var id:Int,
    var locpath:String,
    var netpath:String,
    var ctime:Long,
    var utime:Long,
    var atype:String,
    var duration:Long,
    var name:String,
    var isupload:Boolean,
    var ctype:String,
    var remark:String,
    var parentid:String,
    var enable:Boolean,
)

data class ListData<T>(var data:ArrayList<T>)

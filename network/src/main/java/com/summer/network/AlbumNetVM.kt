package com.summer.network

import com.blankj.utilcode.util.GsonUtils
import com.google.common.reflect.TypeToken
import com.summer.lib.activity.BaseViewModel


class AlbumNetVM:BaseViewModel() {


    suspend inline fun <reified T> 获取所有图片():T{
        return GsonUtils.fromJson(NET.get("picture/getAllPictures"),object : TypeToken<T>() {}.type)
    }
}
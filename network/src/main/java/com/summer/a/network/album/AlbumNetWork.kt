package com.summer.a.network.album

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.summer.a.bean.picture.ListData
import com.summer.a.bean.picture.Picture
import com.summer.a.network.NetWork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
//Failed to connect to /192.168.20.144:8888
object AlbumNetWork {
    /**
     * 获取所有图片
     */
     fun getAllPictures():ArrayList<Picture> {

        var result:Response<ListData<Picture>>? = null
        try {
            result = NetWork.apiService.getAllPictures(startTime = 1668845279069.toString()).execute()
        } catch (e:Exception) {

        }
        return result?.body()?.data?: arrayListOf()
    }
}
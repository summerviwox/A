package com.summer.module.album

import android.Manifest
import android.os.Bundle
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.summer.album.AlbumDBViewModel
import com.summer.bean.picture.ListData
import com.summer.database.album.Item
import com.summer.lib.activity.BaseViewModel
import com.summer.lib.activity.getViewModel
import com.summer.lib.permission.PermissionUtil
import com.summer.network.AlbumNetVM
import com.summer.provider.RouteAnno
import com.summer.view.BaseUIActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RouteAnno("AlbumActivity")
class AlbumActivity : BaseUIActivity() {

    val albumView by lazy {
        AlbumView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e(666)
        ImmersionBar.with(this).init()
        var permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )
        if(!PermissionUtil.requestPermissions(this,permissions)){
            return
        }
        setContentView(albumView)
        lifecycleScope.launch(Dispatchers.IO) {
            var time = System.currentTimeMillis()
            getViewModel<AlbumDBViewModel>().getAll(this@AlbumActivity)
            //LogUtils.e(System.currentTimeMillis() - time)
//            getViewModel<AlbumNetVM>().获取所有图片<ListData<Item>>().data.also {list->
//                LogUtils.e(System.currentTimeMillis() - time)//238160
//                getViewModel<AlbumDBViewModel>().insert(list)
//                getViewModel<AlbumViewModel>().items.postValue(list)
//            }
        }

        onBackPressedDispatcher.addCallback {
            if(albumView.childCount>1){
                albumView.removeViewAt(albumView.childCount-1)
            }
        }

    }
}
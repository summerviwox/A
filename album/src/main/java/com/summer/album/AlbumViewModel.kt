package com.summer.module.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.summer.network.album.AlbumNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class AlbumViewModel: ViewModel() {

    companion object{
        fun instance(viewModelStoreOwner:ViewModelStoreOwner):AlbumViewModel{
            return ViewModelProvider(viewModelStoreOwner)[AlbumViewModel::class.java]
        }
    }

    val flow = MutableSharedFlow<Int>(replay = 5, extraBufferCapacity = 5,BufferOverflow.DROP_OLDEST)


    fun getData(){
        viewModelScope.launch(Dispatchers.Default) {
            var pictures = AlbumNetWork.getAllPictures()
            LogUtils.e(GsonUtils.toJson(pictures))
        }
        viewModelScope.launch {
            flow.collectIndexed { index, value ->
                LogUtils.e("first",index,value)
            }
        }
        viewModelScope.launch {
            for(count in 0..5){
                LogUtils.e("发射:${count}")
                flow.emit(count)
                delay(1000)
            }
        }
        viewModelScope.launch {
            delay(10000)
            flow.collectIndexed { index, value ->
                LogUtils.e("last",index,value)
            }
        }
    }



}
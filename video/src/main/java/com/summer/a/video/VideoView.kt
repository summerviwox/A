package com.summer.a.video

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.getkeepsafe.relinker.ReLinker
import com.summer.a.video.databinding.VideoActivityBinding
import java.io.File

class VideoView @JvmOverloads constructor(
    context:Context, attrs:AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding by lazy {
        VideoActivityBinding.inflate(LayoutInflater.from(context),this,true)
    }

    init {
        var activityResultLauncher = VideoUtil.openFileChooser(context as FragmentActivity,object :VideoCallBack{
            override fun onResult(path:Any?) {
                path?.toString().let {url->
                    url?.let {
                        LogUtils.e(it)
                        //System.load(it)
                        binding.videoPlayer.visibility = GONE
                        Glide.with(context).load("/storage/emulated/0/Download/new/1").into(binding.image)
                    }
                }
            }
        })
        binding.load.setOnClickListener {
            c()
        }
        binding.play.setOnClickListener {
            binding.videoPlayer.run {
                setUp("https://www.w3school.com.cn/i/video/shanghai.mp4",true,"")
                startPlayLogic()
            }
        }

    }

    fun c(){
        LogUtils.e(Build.SUPPORTED_ABIS)
        V25.install(context.classLoader,File(context.filesDir,"/lib/arm64"))
    }

    fun b(){
        var path1 = "${context.filesDir}/lib/arm64/libijkffmpeg.so"
        var path2 = "${context.filesDir}/lib/arm64/libijkplayer.so"
        var path3 = "${context.filesDir}/lib/arm64/libijksdl.so"
        var path4 = "${context.filesDir}/lib/arm64/librtmp-jni.so"
        arrayListOf(path1,path3,path2,path4).forEach {so->
            LogUtils.e(so)
            //System.load(so)
        }

        V25.install(context.classLoader,File(context.filesDir,"/lib/arm64"))
        V25.install(context.classLoader,File(context.filesDir,"/lib/arm64"))
        var a = object :ReLinker.LoadListener{
            override fun success() {
                LogUtils.e("success")
            }

            override fun failure(t:Throwable?) {
                LogUtils.e(t.toString())
            }

        }
        //ReLinker.loadLibrary(context, "ijkffmpeg",a);
        //System.loadLibrary("ijkffmpeg")
        //System.loadLibrary("libijkffmpeg")
    }

    fun a(){
        //VideoUtil.loadSO(context,activityResultLauncher)
        binding.videoPlayer.visibility = GONE
        //var image = "${context.externalCacheDir?.absolutePath}/1"
        var so1 = "${context.externalCacheDir?.absolutePath}/arm64-v8a/libijkffmpeg.so"
        var so2 = "${context.externalCacheDir?.absolutePath}/arm64-v8a/libijkplayer.so"
        var so3 = "${context.externalCacheDir?.absolutePath}/arm64-v8a/libijksdl.so"
        var so4 = "${context.externalCacheDir?.absolutePath}/arm64-v8a/librtmp-jni.so"
        //LogUtils.e(image)
        LogUtils.e(so1)
        LogUtils.e(context.getFilesDir())
        LogUtils.e(context.getFilesDir().listFiles().size)
        var dirs = context.getFilesDir()
        dirs.listFiles().forEach {
            LogUtils.e(it.absolutePath)
        }

        YourLibraryLoader.loadLibrary(context)
        //System.load(so2)
        //Glide.with(context).load(image).into(binding.image)
    }
}
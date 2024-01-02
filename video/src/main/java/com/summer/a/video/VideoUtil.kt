package com.summer.a.video

import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionX


object VideoUtil {

    fun loadSO(activity:FragmentActivity,resultLauncher:ActivityResultLauncher<String>){
        requestPermissions(activity,object :VideoCallBack{
            override fun onResult(any:Any?) {
                resultLauncher.launch("image/*")
            }
        })

    }

    fun openFileChooser(activity:FragmentActivity, callBack:VideoCallBack):ActivityResultLauncher<String> {
        return activity.registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
            uri?.path.let {
                it?.let {
                    //callBack.onResult("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absoluteFile}${it.replace("/document/primary:Download","")}")
                    callBack.onResult("")
                }
            }
        }
    }

    fun requestPermissions(activity:FragmentActivity,callBack:VideoCallBack){
        if (ContextCompat.checkSelfPermission(activity,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            callBack.onResult(null)
            return
        }
        PermissionX.init(activity)
            .permissions(listOf(Manifest.permission.READ_EXTERNAL_STORAGE))
            .request { allGranted, grantedList, deniedList ->
                if(allGranted){
                    callBack.onResult(null)
                }
            }
    }
}
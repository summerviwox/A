package com.summer.a.app.start

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler

class StartModel {

    fun jump(context: Context,clazz: Class<out Activity>){
        Handler().postDelayed({
            context.startActivity(Intent(context,clazz))
        },3000)
    }

}
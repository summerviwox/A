package com.summer.a.app.start

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.LogUtils
import com.summer.a.app.databinding.StartActivityBinding

@SuppressLint("MissingPermission")
class StartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val startActivityBinding by lazy {
        StartActivityBinding.inflate(LayoutInflater.from(context), this)
    }

    init {
        startActivityBinding.x.setOnClickListener {
            //context.startActivity(Intent(context, AlbumActivity::class.java))


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var telephonyManager = ((context as FragmentActivity).getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager)
                val str = telephonyManager.getImei(0)
                LogUtils.e("telephonyManager.getImei(0):$str")
            }
        }

    }
}
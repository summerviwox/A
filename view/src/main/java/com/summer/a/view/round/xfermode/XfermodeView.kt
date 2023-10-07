package com.summer.a.view.round.xfermode

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.helper.widget.Layer
import com.blankj.utilcode.util.LogUtils
import com.summer.a.view.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class XfermodeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var dstColor = Color.parseColor("#5500ff00")
    var srcColor = Color.parseColor("#55ff0000")

    val srcBitmap:Bitmap by lazy {
        BitmapFactory.decodeResource(resources, R.drawable.src)

    }
    val dstBitmap:Bitmap by lazy {
        BitmapFactory.decodeResource(resources, R.drawable.dst)
    }

    val bgBitmap:Bitmap by lazy {
        Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.bg1),width,800,false)
    }

    val bitmap:Bitmap by lazy {
        LogUtils.e(1)
        BitmapFactory.decodeResource(resources,R.drawable.abv)
    }

    val paint:Paint by lazy {
        Paint()
    }

    val mainScope by lazy {
        MainScope()
    };

    var x  = 0


    init {
        setLayerType(Layer.LAYER_TYPE_SOFTWARE,null)
        mainScope.launch {
            while (true){
                x++
               invalidate()
                delay(100)
            }
        }
        LogUtils.e(1)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.also {
            drawText(canvas)
        }
    }



    fun drawText(canvas: Canvas){
        canvas.drawColor(Color.BLACK)
        var layer = canvas.saveLayer(RectF(0f,0f,width.toFloat(),height.toFloat()),paint)
        paint.setColor(Color.RED)
        paint.textSize =800f
        canvas.drawText("qwertyu",0f,1600f,paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_OVER))
        paint.setColor(srcColor)
        //canvas.drawRect((0f+x*10)%width,0f,(500f+x*10)%width,600f,paint)
        canvas.drawBitmap(bitmap,(0f+x*10)%width,800f,paint)
        paint.setXfermode(null)
        canvas.restoreToCount(layer)
    }
    fun drawCanvas(canvas: Canvas){
        canvas.drawColor(Color.BLACK)
        var layer = canvas.saveLayer(RectF(0f,0f,width.toFloat(),height.toFloat()),paint)
        paint.setColor(dstColor)
        canvas.drawRect(0f,0f,600f,600f,paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        paint.setColor(srcColor)
        canvas.drawCircle(600f,600f,300f,paint)
        canvas.restoreToCount(layer)
    }


    fun drawBitmap(canvas: Canvas){
        canvas.drawColor(Color.BLACK)
        var layer = canvas.saveLayer(RectF(0f,0f,width.toFloat(),height.toFloat()),paint)
        canvas.drawBitmap(dstBitmap,0f,0f,paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.XOR))
        canvas.drawBitmap(srcBitmap,0f,0f,paint)
        canvas.restoreToCount(layer)
    }
}
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
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import androidx.constraintlayout.helper.widget.Layer
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.LogUtils
import com.summer.a.view.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * gradientDrawable 不连续问题
 */
class XfermodeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var dstColor = Color.parseColor("#5500ff00")
    var srcColor = Color.parseColor("#55ff0000")
    var rectColor = Color.parseColor("#FFF8F8F9")

    val srcBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(resources, R.drawable.src)

    }
    val dstBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(resources, R.drawable.dst)
    }

    val bgBitmap: Bitmap by lazy {
        Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.bg1), width, 800, false
        )
    }

    var position = 0f
    var lightWidthPercent = 1f

    val lightWidth by lazy {
        lightWidthPercent*width
    }


    val gradientDrawable by lazy {
        var gradientDrawable = GradientDrawable().also {
            it.shape = GradientDrawable.RECTANGLE
            it.gradientType = GradientDrawable.LINEAR_GRADIENT
            it.orientation = GradientDrawable.Orientation.LEFT_RIGHT
            it.colors = intArrayOf(
                resources.getColor(R.color.color_f8f8f8),
                resources.getColor(R.color.color_ff0000),
                resources.getColor(R.color.color_f8f8f8),

//                resources.getColor(R.color.color_ffffff),
//                resources.getColor(R.color.color_ff0000),
//                resources.getColor(R.color.color_ffffff),
//                resources.getColor(R.color.color_ff0000),
//                resources.getColor(R.color.color_ffffff),

            )
            it.setSize(
                (2 *lightWidth).toInt(), height
            )
        }
        ImageUtils.drawable2Bitmap(gradientDrawable)
    }


    val paint: Paint by lazy {
        Paint()
    }

    val mainScope by lazy {
        MainScope()
    };

    val timegap = 40L

    var time = 4000f

    val gap by lazy {
        width/(time/timegap)
    }



    init {
        setLayerType(Layer.LAYER_TYPE_SOFTWARE, null)
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (width != 0) {
                    position = -(2 *lightWidth)
                    mainScope.launch {
                        while (true) {
                            position += gap
                            if (position > 0) {
                                position = -lightWidth
                            }
                            invalidate()
                            delay(timegap)
                        }
                    }
                    LogUtils.e(1)
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }

        })
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.also {
            drawCanvas(canvas)
        }
    }


    fun drawText(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        var layer = canvas.saveLayer(RectF(0f, 0f, width.toFloat(), height.toFloat()), paint)
        paint.setColor(Color.WHITE)
        paint.textSize = 800f
        canvas.drawText("qwertyu", 0f, 1600f, paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.DST_IN))
        //canvas.drawRect((0f+x*10)%width,0f,(500f+x*10)%width,600f,paint)
        canvas.drawBitmap(gradientDrawable, position, 0f, paint)
        paint.setXfermode(null)
        canvas.restoreToCount(layer)
    }

    fun drawCanvas(canvas: Canvas) {
        var layer = canvas.saveLayer(RectF(0f, 0f, width.toFloat(), height.toFloat()), paint)
        paint.setColor(rectColor)
        canvas.drawRect(0f, 300f, width.toFloat(), 1000f, paint)
        //paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(gradientDrawable, position, 0f, paint)
        canvas.restoreToCount(layer)
    }


    fun drawBitmap(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        var layer = canvas.saveLayer(RectF(0f, 0f, width.toFloat(), height.toFloat()), paint)
        canvas.drawBitmap(dstBitmap, 0f, 0f, paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.XOR))
        canvas.drawBitmap(srcBitmap, 0f, 0f, paint)
        canvas.restoreToCount(layer)
    }
}
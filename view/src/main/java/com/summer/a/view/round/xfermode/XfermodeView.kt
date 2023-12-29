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
import com.blankj.utilcode.util.ToastUtils
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
    var rectColor = Color.parseColor("#f8f8f9")
    var color_4DEDEDED = Color.parseColor("#4DEDEDED")

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
    var lightWidthPercent = 2f

    val lightWidth by lazy {
        lightWidthPercent*width
    }


    val gradientDrawable by lazy {
        var gradientDrawable = GradientDrawable().also {
            it.shape = GradientDrawable.RECTANGLE
            it.gradientType = GradientDrawable.LINEAR_GRADIENT
            it.orientation = GradientDrawable.Orientation.LEFT_RIGHT
            it.colors = intArrayOf(
                resources.getColor(R.color.color_f8f8f8,null),
                resources.getColor(R.color.color_ccff0000,null),
                resources.getColor(R.color.color_f8f8f8,null),

            )
            it.setSize(
                (lightWidth).toInt(), height
            )
        }
        //不要将drawble转换成bitmap不然渐变色会因为尺寸缩放而失真 使用drawable.draw(canvas)
        //ImageUtils.drawable2Bitmap(gradientDrawable)
        gradientDrawable
    }

    val gradientBitmap by lazy {
        var gradientDrawable = GradientDrawable().also {
            it.shape = GradientDrawable.RECTANGLE
            it.gradientType = GradientDrawable.LINEAR_GRADIENT
            it.orientation = GradientDrawable.Orientation.LEFT_RIGHT
            it.colors = intArrayOf(
                resources.getColor(R.color.color_f8f8f9),//起止颜色和目标图像颜色相似才会有渐变感
                resources.getColor(R.color.color_4DEDEDED),
                resources.getColor(R.color.color_f8f8f9),

                )
            it.setSize(
                (lightWidth).toInt(), height
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
                    position = -lightWidth
                    mainScope.launch {
                        while (true) {
                            position += gap
                            if (position >= width) {
                                ToastUtils.showLong("0")
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

    override fun draw(canvas: Canvas) {
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
        //paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.DST_IN))
        //canvas.drawRect((0f+x*10)%width,0f,(500f+x*10)%width,600f,paint)
        //canvas.drawBitmap(gradientDrawable, position, 0f, paint)
        paint.setXfermode(null)
        canvas.restoreToCount(layer)
    }


    fun drawCanvas1(canvas: Canvas) {
        paint.setColor(Color.YELLOW)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

        var layer = canvas.saveLayer(RectF(0f, 0f, width.toFloat(), height.toFloat()), paint)
        paint.setColor(Color.BLUE)
        paint.textSize = 500f
        canvas.drawText("5345345",0f,500f,paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        paint.setColor(Color.GREEN)
        canvas.drawCircle(400f,400f,400f,paint)
        paint.setXfermode(null)
        canvas.restoreToCount(layer)
    }

    fun drawCanvas(canvas: Canvas) {
        paint.setColor(Color.BLACK)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        var layer = canvas.saveLayer(RectF(0f, 0f, width.toFloat(), height.toFloat()), paint)
        paint.textSize = 500f
        paint.setColor(rectColor)
        //canvas.drawText("5345345",0f,500f,paint)
        canvas.drawRect(0f,0f,width.toFloat(),height.toFloat(),paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(gradientBitmap, position, 0f, paint)
        //gradientDrawable.setBounds(position.toInt(),0,position.toInt()+gradientDrawable.intrinsicWidth,gradientDrawable.intrinsicHeight)
        //gradientDrawable.draw(canvas)
        //paint.setColor(color_4DEDEDED)
        //canvas.drawRect(position,0f,position+200f,height.toFloat(),paint)
        paint.setXfermode(null)
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
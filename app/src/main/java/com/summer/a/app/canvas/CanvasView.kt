package com.summer.a.app.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint by lazy {
        Paint()
    }
    private var x = -100f;
    private var text = "你给的敬爱苹果IE评为飞机丿"

    init {
        GlobalScope.launch {
            while (true) {
                x += 1;
                postInvalidate()
                delay(5)
                if (x > width * 2 / 3) {
                    x = -100f
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            this.drawARGB(255, 200, 200, 200)
            var r = (width / 3).toFloat()
            paint.setColor(Color.RED)
            this.drawCircle(r, r, r, paint)
            var layer = this.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
            paint.setColor(Color.GREEN)
            paint.textSize = 50f
            canvas.drawText(text, 0f, r, paint)
            paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
            paint.setColor(Color.WHITE)
            paint.alpha = 200
            this.drawRect(x, 0f, x + 100f, 2 * r, paint)
            this.restoreToCount(layer)
            paint.setXfermode(null)
        }
    }
}
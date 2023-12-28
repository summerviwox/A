package com.summer.a.app.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasView1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val w by lazy {
        width / 2f
    }
    private val h by lazy {
        height / 2f
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        paint.setColor(Color.RED)
        canvas?.apply {
            this.drawCircle(0f, 0f, w, paint)
            canvas.translate(w, h)
            this.drawCircle(0f, 0f, w, paint)
        }
    }
}
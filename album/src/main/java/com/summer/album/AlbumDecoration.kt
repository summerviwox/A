package com.summer.album

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.summer.database.album.Item
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class AlbumDecoration(val context: Context,val items:MutableList<Item>): ItemDecoration() {

    val paint = Paint().also {
        it.color = context.resources.getColor(com.summer.view.R.color.color_ff0000)
        it.textSize = SizeUtils.dp2px(20f).toFloat()
        it.isAntiAlias = true
    }
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos = parent.getChildAdapterPosition(view)
        if(items[pos].top==1||items[pos].top==2){
            outRect.top = 100;
        }
    }
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        for(count in 0 until parent.childCount){
            val index = parent.getChildAdapterPosition(parent.getChildAt(count))
            if(index>=items.size||index<0){
                return
            }
            if(items.get(index).top==1){
                Calendar.getInstance().also {
                    val d = Date(items.get(index).ctime)
                    it.time = d
                    val df = SimpleDateFormat("yyyy-MM-dd")
                    df.timeZone = TimeZone.getTimeZone("GMT+8")
                    c.drawText(df.format(d),parent.getChildAt(count).left.toFloat(),parent.getChildAt(count).top.toFloat()-SizeUtils.dp2px(10f).toFloat(),paint)
                }

            }
        }
    }
}
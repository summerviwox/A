package com.summer.a.album

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.constraintlayout.widget.ConstraintLayout
import com.summer.a.album.databinding.AlbumActivityBinding

/**
 * 相册view
 */
class AlbumView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding: AlbumActivityBinding

    init {
        viewBinding = AlbumActivityBinding.inflate(LayoutInflater.from(context), this, true)
        viewTreeObserver.addOnGlobalLayoutListener(object :OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                if(width!=0){
                    viewBinding.monthsView.setData()
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }

        })
    }

}
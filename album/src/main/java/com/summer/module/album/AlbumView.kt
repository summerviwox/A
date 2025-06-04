package com.summer.module.album

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.summer.album.databinding.AlbumActivityBinding

/**
 * 相册view
 */
class AlbumView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val mBinding: AlbumActivityBinding

    init {
        mBinding = AlbumActivityBinding.inflate(LayoutInflater.from(context), this, true)
    }
}
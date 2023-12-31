package com.summer.a.app.start

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.summer.a.app.databinding.StartActivityBinding

class StartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val startActivityBinding by lazy {
        StartActivityBinding.inflate(LayoutInflater.from(context), this)
    }

    init {


    }
}
package com.summer.a.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.summer.a.app.R

class MainActivity:FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

    }


}
package com.summer.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.summer.a.tv.databinding.APresenterBinding

class APresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(APresenterBinding.inflate(LayoutInflater.from(parent?.context)).root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }
}
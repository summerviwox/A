package com.summer.notes

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager.LayoutParams
import com.blankj.utilcode.util.KeyboardUtils
import com.summer.screenmatch.R
import com.summer.notes.databinding.NotesDialogBinding

class NotesDialog(context: Context,out:(String)->Unit) : Dialog(context) {

    val binding by lazy { NotesDialogBinding.inflate(LayoutInflater.from(context),null,false) }
    init {
        binding.root.setRadius(context.resources.getDimension(R.dimen.sw_15dp))
        var params = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        params.copyFrom(window?.attributes)
        params.width = context.resources.getDimensionPixelSize(R.dimen.sw_720dp)
        params.height = LayoutParams.WRAP_CONTENT
        params.gravity =Gravity.BOTTOM
        window?.setContentView(binding.root)
        window?.attributes = params
        setOnDismissListener {
            out.invoke(binding.edit.text.toString())
        }
    }

    override fun show() {
        super.show()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.edit.requestFocus()
            KeyboardUtils.showSoftInput(binding.edit)
        },50)
    }

}
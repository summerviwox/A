package com.summer.web

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebView

@SuppressLint("JavascriptInterface")
class CustomWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : WebView(context, attrs) {

    init {
        settings.apply {
            javaScriptEnabled = true
            setSupportZoom(true)
            displayZoomControls = true
            builtInZoomControls = false
        }
        webChromeClient = WebChromeClient()
        addJavascriptInterface(JS(), "newSdkInterface")
    }
}
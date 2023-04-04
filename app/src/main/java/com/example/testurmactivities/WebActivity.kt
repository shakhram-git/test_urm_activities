package com.example.testurmactivities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.testurmactivities.databinding.ActivityWebBinding


class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                binding.progress.isVisible = newProgress != 100
                super.onProgressChanged(view, newProgress)
            }
        }
        binding.webView.loadUrl(URL)
        val settings = binding.webView.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()) {
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {
        const val URL = "https://www.google.com/"
    }
}
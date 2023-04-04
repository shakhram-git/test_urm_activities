package com.example.testurmactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerClient.InstallReferrerResponse
import com.android.installreferrer.api.InstallReferrerStateListener
import com.example.testurmactivities.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var referrerClient: InstallReferrerClient
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        referrerClient = InstallReferrerClient.newBuilder(this).build()
        val referrerListener = object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerResponse.OK -> {
                        val data = referrerClient.installReferrer.installReferrer
                        val referrers = data?.split("&")
                        val source = /*"magazin"*/
                            referrers?.firstOrNull { it.contains("utm_source") }
                                ?.substringAfter("=")
                        if (source.isNullOrBlank() || source == "google-play") {
                            startActivity(Intent(this@SplashActivity, GameActivity::class.java))
                            finish()
                        }
                        else {
                            startActivity(Intent(this@SplashActivity, WebActivity::class.java))
                            finish()
                        }
                    }
                    InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {

                    }
                    InstallReferrerResponse.SERVICE_UNAVAILABLE -> {

                    }
                }

            }

            override fun onInstallReferrerServiceDisconnected() {
                referrerClient.startConnection(this)
            }
        }
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        referrerClient.startConnection(referrerListener)


    }

}
package com.hencoder.okhttp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.CertificatePinner
import java.io.IOException


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val url = "https://api.github.com/users/rengwuxian/repos"
    val hostname = "api.github.com"

    val client = OkHttpClient.Builder()
      .build()
    val request: Request = Request.Builder()
      .url(url)
      .build()
    client.newCall(request)
      .enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
          e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
          println("Response status code: ${response.code}")
        }
      })
  }
}
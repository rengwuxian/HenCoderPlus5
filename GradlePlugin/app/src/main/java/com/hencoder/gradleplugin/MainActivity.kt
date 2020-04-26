package com.hencoder.gradleplugin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    val startTime = SystemClock.uptimeMillis()
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val timePassed = SystemClock.uptimeMillis() - startTime
    if (timePassed > 500) {
      Log.d("方法执行时间过长", "onCreate: $timePassed")
    }
  }
}
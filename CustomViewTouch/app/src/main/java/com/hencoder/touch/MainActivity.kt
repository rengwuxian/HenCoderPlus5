package com.hencoder.touch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    view.setOnClickListener {
      Toast.makeText(this@MainActivity, "点击了！", Toast.LENGTH_SHORT).show()
    }
  }
}
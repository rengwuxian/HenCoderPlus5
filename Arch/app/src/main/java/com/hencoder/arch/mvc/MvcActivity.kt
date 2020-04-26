package com.hencoder.arch.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hencoder.arch.DataCenter
import com.hencoder.arch.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.data1View
import kotlinx.android.synthetic.main.activity_main.data2View
import kotlinx.android.synthetic.main.activity_main_mvc.*

class MvcActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_mvc)

    val data = DataCenter.getData()
    dataView.showData(data)
  }
}
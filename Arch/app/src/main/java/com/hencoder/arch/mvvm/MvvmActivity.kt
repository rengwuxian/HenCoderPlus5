package com.hencoder.arch.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hencoder.arch.DataCenter
import com.hencoder.arch.R
import com.hencoder.arch.mvp.Presenter
import kotlinx.android.synthetic.main.activity_main.*

class MvvmActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    ViewModel(data1View, data2View).init()
  }
}
package com.hencoder.arch.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hencoder.arch.DataCenter
import com.hencoder.arch.R
import kotlinx.android.synthetic.main.activity_main.*

class MvpActivity : AppCompatActivity(), Presenter.IView {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    Presenter(this).init()
  }

  override fun showData(data: List<String>) {
    data1View.setText(data[0])
    data2View.setText(data[1])
  }
}
package com.hencoder.arch.mvc

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main_mvc.view.*

class MvcView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
  fun showData(data: List<String>) {
    data1View.setText(data[0])
    data2View.setText(data[1])
  }
}
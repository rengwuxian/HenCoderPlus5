package com.hencoder.arch.mvvm

import android.widget.EditText
import com.hencoder.arch.DataCenter

class ViewModel(data1View: EditText, data2View: EditText) {
  var data1: StringAttr = StringAttr()
  var data2: StringAttr = StringAttr()

  init {
    ViewBinder.bind(data1View, data1)
    ViewBinder.bind(data2View, data2)
  }

  fun init() {
    val data = DataCenter.getData()
    data1.value = data[0]
    data2.value = data[1]
  }
}
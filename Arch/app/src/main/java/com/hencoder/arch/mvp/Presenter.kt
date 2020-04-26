package com.hencoder.arch.mvp

import com.hencoder.arch.DataCenter

class Presenter(private val iView: IView) {
  fun init() {
    val data = DataCenter.getData()
    iView.showData(data)
  }

  interface IView {
    fun showData(data: List<String>)
  }
}
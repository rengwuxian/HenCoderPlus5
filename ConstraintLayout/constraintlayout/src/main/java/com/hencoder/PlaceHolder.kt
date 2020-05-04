package com.hencoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Placeholder
import com.hencoder.constraintlayout.R

class PlaceHolder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_holder)
    }

    fun onClick(view: View) {
        findViewById<Placeholder>(R.id.placeholder).setContentId(view.id)
    }
}

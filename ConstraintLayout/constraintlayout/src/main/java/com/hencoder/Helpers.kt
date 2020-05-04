package com.hencoder

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.Group

import com.hencoder.constraintlayout.R

class Helpers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_group)
//        findViewById<Button>(R.id.button).setOnClickListener {
//            findViewById<Group>(R.id.group).visibility = View.GONE
//        }


//        setContentView(R.layout.activity_layer)
//        findViewById<Button>(R.id.button).setOnClickListener {
//            findViewById<Layer>(R.id.layer).rotation = 45f
//            findViewById<Layer>(R.id.layer).translationY = 100f
//            findViewById<Layer>(R.id.layer).translationX = 100f
//        }
//
        setContentView(R.layout.activity_barrier)

    }
}

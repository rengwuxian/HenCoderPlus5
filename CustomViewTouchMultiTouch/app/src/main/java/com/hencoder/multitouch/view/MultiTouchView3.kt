package com.hencoder.multitouch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.hencoder.multitouch.dp

class MultiTouchView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private var paths = SparseArray<Path>()

  init {
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 4.dp
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeJoin = Paint.Join.ROUND
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    for (i in 0 until paths.size()) {
      val path = paths.valueAt(i)
      canvas.drawPath(path, paint)
    }
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    when (event.actionMasked) {
      MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
        val actionIndex = event.actionIndex
        val path = Path()
        path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
        paths.append(event.getPointerId(actionIndex), path)
        invalidate()
      }
      MotionEvent.ACTION_MOVE -> {
        for (i in 0 until paths.size()) {
          val pointerId = event.getPointerId(i)
          val path = paths.get(pointerId)
          path.lineTo(event.getX(i), event.getY(i))
        }
        invalidate()
      }
      MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
        val actionIndex = event.actionIndex
        val pointerId = event.getPointerId(actionIndex)
        paths.remove(pointerId)
        invalidate()
      }
    }
    return true
  }
}
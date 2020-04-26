package com.hencoder.drag.view


import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import kotlinx.android.synthetic.main.drag_up_down.view.*


class DragUpDownLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
  private var dragListener: ViewDragHelper.Callback = DragCallback()
  private var dragHelper: ViewDragHelper = ViewDragHelper.create(this, dragListener)
  private var viewConfiguration: ViewConfiguration = ViewConfiguration.get(context)

  override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
    return dragHelper.shouldInterceptTouchEvent(ev)
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    dragHelper.processTouchEvent(event)
    return true
  }

  override fun computeScroll() {
    if (dragHelper.continueSettling(true)) {
      ViewCompat.postInvalidateOnAnimation(this)
    }
  }

  internal inner class DragCallback : ViewDragHelper.Callback() {
    override fun tryCaptureView(child: View, pointerId: Int): Boolean {
      return child === draggedView
    }

    override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
      return top
    }

    override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
      if (Math.abs(yvel) > viewConfiguration.scaledMinimumFlingVelocity) {
        if (yvel > 0) {
          dragHelper.settleCapturedViewAt(0, height - releasedChild.height)
        } else {
          dragHelper.settleCapturedViewAt(0, 0)
        }
      } else {
        if (releasedChild.top < height - releasedChild.bottom) {
          dragHelper.settleCapturedViewAt(0, 0)
        } else {
          dragHelper.settleCapturedViewAt(0, height - releasedChild.height)
        }
      }
      postInvalidateOnAnimation()
    }
  }
}
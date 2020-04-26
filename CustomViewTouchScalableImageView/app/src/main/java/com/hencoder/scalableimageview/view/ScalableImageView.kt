package com.hencoder.scalableimageview.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import android.widget.Scroller
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.hencoder.scalableimageview.dp
import com.hencoder.scalableimageview.getAvatar
import kotlin.math.max
import kotlin.math.min

private val IMAGE_SIZE = 300.dp.toInt()
private const val EXTRA_SCALE_FACTOR = 1.5f

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val bitmap = getAvatar(resources, IMAGE_SIZE)
  private var originalOffsetX = 0f
  private var originalOffsetY = 0f
  private var offsetX = 0f
  private var offsetY = 0f
  private var smallScale = 0f
  private var bigScale = 0f
  private val henGestureListener = HenGestureListener()
  private val henScaleGestureListener = HenScaleGestureListener()
  private val henFlingRunner = HenFlingRunner()
  private val gestureDetector = GestureDetectorCompat(context, henGestureListener)
  private val scaleGestureDetector = ScaleGestureDetector(context, henScaleGestureListener)
  private var big = false
  private var currentScale = 0f
    set(value) {
      field = value
      invalidate()
    }
  private val scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
  private val scroller = OverScroller(context)

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)

    originalOffsetX = (width - IMAGE_SIZE) / 2f
    originalOffsetY = (height - IMAGE_SIZE) / 2f

    if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
      smallScale = width / bitmap.width.toFloat()
      bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
    } else {
      smallScale = height / bitmap.height.toFloat()
      bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
    }
    currentScale = smallScale
    scaleAnimator.setFloatValues(smallScale, bigScale)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    scaleGestureDetector.onTouchEvent(event)
    if (!scaleGestureDetector.isInProgress) {
      gestureDetector.onTouchEvent(event)
    }
    return true
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
    canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
    canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
    canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
  }

  private fun fixOffsets() {
    offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
    offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
    offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
    offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
  }

  inner class HenGestureListener : GestureDetector.SimpleOnGestureListener() {
    override fun onDown(e: MotionEvent): Boolean {
      return true
    }

    override fun onFling(
      downEvent: MotionEvent?,
      currentEvent: MotionEvent?,
      velocityX: Float,
      velocityY: Float
    ): Boolean {
      if (big) {
        scroller.fling(offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
          (- (bitmap.width * bigScale - width) / 2).toInt(),
          ((bitmap.width * bigScale - width) / 2).toInt(),
          (- (bitmap.height * bigScale - height) / 2).toInt(),
          ((bitmap.height * bigScale - height) / 2).toInt()
        )
        ViewCompat.postOnAnimation(this@ScalableImageView, henFlingRunner)
      }
      return false
    }

    override fun onScroll(
      downEvent: MotionEvent?,
      currentEvent: MotionEvent?,
      distanceX: Float,
      distanceY: Float
    ): Boolean {
      if (big) {
        offsetX -= distanceX
        offsetY -= distanceY
        fixOffsets()
        invalidate()
      }
      return false
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
      big = !big
      if (big) {
        offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
        offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
        fixOffsets()
        scaleAnimator.start()
      } else {
        scaleAnimator.reverse()
      }
      return true
    }
  }

  inner class HenScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
      offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
      offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
      return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {

    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
      val tempCurrentScale = currentScale * detector.scaleFactor
      if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
        return false
      } else {
        currentScale *= detector.scaleFactor // 0 1; 0 无穷
        return true
      }
    }
  }

  inner class HenFlingRunner : Runnable {
    override fun run() {
      if (scroller.computeScrollOffset()) {
        offsetX = scroller.currX.toFloat()
        offsetY = scroller.currY.toFloat()
        invalidate()
        ViewCompat.postOnAnimation(this@ScalableImageView, this)
      }
    }
  }
}
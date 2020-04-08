package com.hencoder.materialedittext

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

private val TEXT_SIZE = 12.dp
private val TEXT_MARGIN = 8.dp
private val HORIZONTAL_OFFSET = 5.dp
private val VERTICAL_OFFSET = 23.dp
private val EXTRA_VERTICAL_OFFSET = 16.dp

class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private var floatingLabelShown = false
  var floatingLabelFraction = 0f
    set(value) {
      field = value
      invalidate()
    }
  private val animator by lazy {
    ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
  }
  var useFloatingLabel = false
    set(value) {
      if (field != value) {
        field = value
        if (field) {
          setPadding(paddingLeft, (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(), paddingRight, paddingBottom)
        } else {
          setPadding(paddingLeft, (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(), paddingRight, paddingBottom)
        }
      }
    }

  init {
    paint.textSize = TEXT_SIZE

    for (index in 0 until attrs.attributeCount) {
      println("Attrs: key: ${attrs.getAttributeName(index)}, value: ${attrs.getAttributeValue(index)}")
    }

    val typedArray = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatingLabel))
    useFloatingLabel = typedArray.getBoolean(0, true)
    typedArray.recycle()
  }

  override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
    if (floatingLabelShown && text.isNullOrEmpty()) {
      floatingLabelShown = false
      animator.reverse()
    } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
      floatingLabelShown = true
      animator.start()
    }
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    paint.alpha = (floatingLabelFraction * 0xff).toInt()
    val currentVertivalValue = VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction)
    canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVertivalValue, paint)
  }
}
package com.hencoder.layoutlayout.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
  private val childrenBounds = mutableListOf<Rect>()

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    var widthUsed = 0
    var heightUsed = 0
    var lineWidthUsed = 0
    var lineMaxHeight = 0
    val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
    val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)
    for ((index, child) in children.withIndex()) {
      measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)

      if (specWidthMode != MeasureSpec.UNSPECIFIED &&
        lineWidthUsed + child.measuredWidth > specWidthSize) {
        lineWidthUsed = 0
        heightUsed += lineMaxHeight
        lineMaxHeight = 0
        measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
      }

      if (index >= childrenBounds.size) {
        childrenBounds.add(Rect())
      }
      val childBounds = childrenBounds[index]
      childBounds.set(lineWidthUsed, heightUsed, lineWidthUsed + child.measuredWidth, heightUsed + child.measuredHeight)

      lineWidthUsed += child.measuredWidth
      widthUsed = max(widthUsed, lineWidthUsed)
      lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
    }
    val selfWidth = widthUsed
    val selfHeight = heightUsed + lineMaxHeight
    setMeasuredDimension(selfWidth, selfHeight)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    for ((index, child) in children.withIndex()) {
      val childBounds = childrenBounds[index]
      child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
    }
  }

  override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
    return MarginLayoutParams(context, attrs)
  }
}
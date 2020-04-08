package com.hencoder.drawing.view

import android.content.res.Resources
import android.util.TypedValue

val Float.px
 get() = TypedValue.applyDimension(
   TypedValue.COMPLEX_UNIT_DIP,
   this,
   Resources.getSystem().displayMetrics
 )
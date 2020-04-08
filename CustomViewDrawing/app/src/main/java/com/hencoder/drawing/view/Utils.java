package com.hencoder.drawing.view;

import android.content.res.Resources;
import android.util.TypedValue;

class Utils {
  public static float dp2px(float value) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().getDisplayMetrics());
  }
}

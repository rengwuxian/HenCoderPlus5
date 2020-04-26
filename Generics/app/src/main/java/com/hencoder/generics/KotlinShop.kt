package com.hencoder.generics

import com.hencoder.generics.fruit.Apple
import com.hencoder.generics.fruit.Fruit

class KotlinShop<out T : Fruit> {
  fun buy(): T {
    return null as T
  }

  fun refund(item: T): Float {
    return 1f
  }
}
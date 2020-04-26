package com.hencoder.generics

import com.hencoder.generics.fruit.Apple
import com.hencoder.generics.fruit.Fruit

class KotlinTest {
  fun main() {
    val kotlinShop: KotlinShop<out Fruit> = KotlinShop<Apple>() // use-site variance
    val kotlinShop1: KotlinShop<out Fruit> = KotlinShop<Apple>()
    val kotlinShop3: KotlinShop<out Fruit> = KotlinShop<Apple>()
    val kotlinShop4: KotlinShop<out Fruit> = KotlinShop<Apple>()
    val kotlinShop2: KotlinShop<in Apple> = KotlinShop<Fruit>()
    val kotlinShop5: KotlinShop<out Fruit> = KotlinShop<Fruit>()
  }
}
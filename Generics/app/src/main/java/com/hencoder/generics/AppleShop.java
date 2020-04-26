package com.hencoder.generics;

import com.hencoder.generics.fruit.Apple;

interface AppleShop extends Shop<Apple> {
  @Override
  Apple buy();

  @Override
  float refund(Apple item); // bridge method
}

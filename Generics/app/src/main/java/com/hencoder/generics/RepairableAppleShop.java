package com.hencoder.generics;

import com.hencoder.generics.fruit.Apple;

class RepairableAppleShop implements RepairableShop<Apple> {
  @Override
  public void repair(Apple item) {

  }

  @Override
  public Apple buy() {
    return null;
  }

  @Override
  public float refund(Apple item) {
    return 0;
  }
}

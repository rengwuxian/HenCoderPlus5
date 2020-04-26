package com.hencoder.generics;

import com.hencoder.generics.fruit.Apple;
import com.hencoder.generics.fruit.Banana;
import com.hencoder.generics.fruit.Fruit;

import java.util.List;

interface HenCoderShopList<T extends List<Shop<Apple>>> extends List<Shop<T>> {
}

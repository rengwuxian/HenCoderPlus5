package com.hencoder.generics;

interface RepairableShop<E> extends Shop<E> {
  void repair(E item);
}

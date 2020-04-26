package com.hencoder.generics;

import java.util.Arrays;

class HenCoderList<T> {
  private Object[] instances = new Object[0];

  public T get(int index) {
    return (T) instances[index];
  }

  public void set(int index, T newInstance) {
    instances[index] = newInstance;
  }

  public void add(T newInstance) {
    instances = Arrays.copyOf(instances, instances.length + 1);
    instances[instances.length - 1] = newInstance;
  }
}

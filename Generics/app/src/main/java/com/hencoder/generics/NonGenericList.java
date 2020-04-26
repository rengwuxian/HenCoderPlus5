package com.hencoder.generics;

import java.util.Arrays;

class NonGenericList {
  private Object[] instances = new Object[0];

  public Object get(int index) {
    return instances[index];
  }

  public void set(int index, Object newInstance) {
    instances[index] = newInstance;
  }

  public void add(Object newInstance) {
    instances = Arrays.copyOf(instances, instances.length + 1);
    instances[instances.length - 1] = newInstance;
  }
}

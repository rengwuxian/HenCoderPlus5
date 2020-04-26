package com.hencoder.generics;

class Wrapper<T> {
  private T instance;

  public T get() {
    return instance;
  }

  public void set(T newInstance) {
    instance = newInstance;
  }
}

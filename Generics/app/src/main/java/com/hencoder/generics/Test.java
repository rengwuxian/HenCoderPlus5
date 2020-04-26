package com.hencoder.generics;

import java.util.List;
import java.util.ArrayList;

public class Test<T extends String> {
  private T value;

  public T getValue() {
    return value;
  }
  public void setValue(T newValue) {
    value = newValue;
  }

  List<String> list = new ArrayList<String>();
}
package com.hencoder.generics;

import java.util.List;

interface Shop<T> { // Type Parameter; Type Argument
  T buy();

  float refund(T item);

  <O> List<T> recycle(O item);

  <E> E tradeIn(E item, float money);

  <R> R take();

  <P> Shop<P> merge(List<P> list1, List<P> list2);
}

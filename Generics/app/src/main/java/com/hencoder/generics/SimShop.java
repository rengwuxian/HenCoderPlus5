package com.hencoder.generics;

import com.hencoder.generics.sim.Sim;

import java.io.Closeable;

interface SimShop<T, C extends Sim & Cloneable & Runnable & Closeable> extends Shop<T> {
  C getSim(String name, String id);
}

package com.hencoder.threadsync;

class SingleMan {
  private static volatile SingleMan sInstance;

  private SingleMan() {
  }

  static SingleMan newInstance() {
    if (sInstance == null) {
      synchronized (SingleMan.class) {
        if (sInstance == null) {
          sInstance = new SingleMan();
        }
      }
    }
    return sInstance;
  }
}

package com.hencoder.threadsync;

import java.util.concurrent.atomic.AtomicBoolean;

public class Synchronized1Demo implements TestDemo {
  private AtomicBoolean running = new AtomicBoolean(true);

  private void stop() {
    running.set(false);
  }

  @Override
  public void runTest() {
    new Thread() {
      @Override
      public void run() {
        while (running.get()) {
        }
      }
    }.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    stop();
  }
}

package com.hencoder.threadandroid;

public class Main {
  public static void main(String[] args) {
    final ThreadLocal<Integer> threadNumber = new ThreadLocal<>();

    new Thread() {
      @Override
      public void run() {
        ...;
        threadNumber.set(1);
        threadNumber.get(); //1
      }
    }.start();
    new Thread() {
      @Override
      public void run() {
        ...;
        threadNumber.set(2);
        threadNumber.get(); // 2
      }
    }.start();
  }
}

package com.jws.settings.utils;

import java.io.Serializable;

public class ThreadUtil implements Serializable {

  private static final long serialVersionUID = 1L;

//  public static ConcurrentHashMap<Long, Semaphore> playerPointSemaphore = new ConcurrentHashMap<>();
//
//  public static ConcurrentHashMap<Long, Semaphore> partnerPointSemaphore = new ConcurrentHashMap<>();

  public static void setTimeout(Runnable runnable, int delay) {
    new Thread(() -> {
      try {
        Thread.sleep(delay);
        runnable.run();
      } catch (Exception e) {
        System.err.println(e);
      }
    }).start();
  }

  public static void newThread(Runnable runnable) {
    new Thread(() -> {
      try {
        runnable.run();
      } catch (Exception e) {
        System.err.println(e);
      }
    }).start();
  }
}

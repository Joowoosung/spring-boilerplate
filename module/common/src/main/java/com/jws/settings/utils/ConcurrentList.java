package com.jws.settings.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentList<T> {

  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private final List<T> list;

  public ConcurrentList() {
    this.list = new ArrayList<>();
  }

  public ConcurrentList(List<T> list) {
    this.list = list;
  }

  public boolean remove(Object o) {
    readWriteLock.writeLock().lock();
    boolean ret;
    try {
      ret = list.remove(o);
    } finally {
      readWriteLock.writeLock().unlock();
    }
    return ret;
  }

  public T remove(int idx) {
    readWriteLock.writeLock().lock();
    T ret;
    try {
      ret = list.remove(idx);
    } finally {
      readWriteLock.writeLock().unlock();
    }
    return ret;
  }

  public boolean add(T t) {
    readWriteLock.writeLock().lock();
    boolean ret;
    try {
      ret = list.add(t);
    } finally {
      readWriteLock.writeLock().unlock();
    }
    return ret;
  }

  public void clear() {
    readWriteLock.writeLock().lock();
    try {
      list.clear();
    } finally {
      readWriteLock.writeLock().unlock();
    }
  }


  public int size() {
    readWriteLock.readLock().lock();
    try {
      return list.size();
    } finally {
      readWriteLock.readLock().unlock();
    }
  }

  public boolean isEmpty() {
    readWriteLock.readLock().lock();
    try {
      return list.isEmpty();
    } finally {
      readWriteLock.readLock().unlock();
    }
  }

  public boolean contains(Object o) {
    readWriteLock.readLock().lock();
    try {
      return list.contains(o);
    } finally {
      readWriteLock.readLock().unlock();
    }
  }

  public T get(int index) {
    readWriteLock.readLock().lock();
    try {
      return list.get(index);
    } finally {
      readWriteLock.readLock().unlock();
    }
  }

  public int indexOf(Object o) {
    readWriteLock.readLock().lock();
    try {
      return list.indexOf(o);
    } finally {
      readWriteLock.readLock().unlock();
    }
  }

  public int lastIndexOf(Object o) {
    readWriteLock.readLock().lock();
    try {
      return list.lastIndexOf(o);
    } finally {
      readWriteLock.readLock().unlock();
    }
  }

  public void sort(Comparator<? super T> c) {
    readWriteLock.readLock().lock();
    try {
      list.sort(c);
    } finally {
      readWriteLock.readLock().unlock();
    }
  }

//etc
}

package com.stone.juc1;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写写/读写需要“互斥”
 * 读读不需要互斥
 */
public class TestReadWriteLock {
	
	public static void main(String[] args) {
		
		ReadWriteDemo rw = new ReadWriteDemo();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				rw.set((int)(Math.random() * 101));
				
			}
		},"write:").start();
		
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					rw.get();
				}
			}).start();
		}
	}

}

class ReadWriteDemo{
	
	private int number = 0;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	//读
	public void get() {
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " : " + number);
		} finally {
			lock.readLock().unlock();
		}
	}
	
	//写
	public void set(int number) {
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName());
			this.number = number;
		} finally {
			lock.writeLock().unlock();
		}
	}
	
}

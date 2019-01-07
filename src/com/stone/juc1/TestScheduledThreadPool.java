package com.stone.juc1;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestScheduledThreadPool {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
		
		for (int i = 0; i < 10; i++) {
			ScheduledFuture<Integer> future = pool.schedule(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					int number = new Random().nextInt(100);
					System.out.println(Thread.currentThread().getName() + " : " + number);
					return number;
				}
			}, 1, TimeUnit.SECONDS);
			
			System.out.println(future.get());
		}
		
		pool.shutdown();
	}
}
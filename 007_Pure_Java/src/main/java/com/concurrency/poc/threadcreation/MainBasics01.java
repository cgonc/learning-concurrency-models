package com.concurrency.poc.threadcreation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainBasics01 {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			log.info("We are in the Thread[{}] is executing.", Thread.currentThread().getName());
			log.info("The priorty[{}]", Thread.currentThread().getPriority());
			throw new RuntimeException("A critical error happened");
		});

		log.info("Thread[{}] is executing before starting a new thread.", Thread.currentThread().getName());
		thread.setName("An Experimental thread");
		thread.start();
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.setUncaughtExceptionHandler((t, e) -> {
			log.info("Thread exception handler");
			log.info("A critical error happened t[{}] e[{}]", t.getName(), e.getMessage());
		});
		log.info("Thread[{}] is executing after starting a new thread.", Thread.currentThread().getName());
		Thread.sleep(10000);
	}
}

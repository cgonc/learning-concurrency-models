package com.concurrency.poc.threadinterrrupted;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main001 {

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			try{
				Thread.sleep(500000);
			} catch (InterruptedException e){
				log.error("Thread is interrupted {}", e.getMessage());
			}
		});

		thread.start();
		thread.interrupt();
	}

}

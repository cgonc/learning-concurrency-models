package com.concurrency.poc.executorservicepoc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorServicePOC {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		log.info("Starting main process");
		for(int i = 0; i < 10; i++){
			int finalI = i;
			executorService.submit(() -> {
				try{
					log.info("{} entering {}", Thread.currentThread().getName(), finalI);
					Thread.sleep(300L);
					log.info("{} exiting", Thread.currentThread().getName());
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			});
		}
		//executorService.shutdown();
		log.info("Proceeding main process..");

	}
}

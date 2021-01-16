package com.concurrency.poc.threadinterrrupted;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Main003 {

	public static ExecutorService mainExecutor = Executors.newFixedThreadPool(100);
	public static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

	public static void main(String[] args) throws InterruptedException {
		ExecutorCompletionService<Boolean> executorCompletionService = new ExecutorCompletionService<>(mainExecutor);

		for(int i = 0; i < 500; i++){
			int counter = i;
			executorCompletionService.submit(() -> {
				log.info(counter + "");
				Thread.sleep(300);
				return true;
			});
		}
		log.info("calling take");
		for(int i = 0; i < 500; i++){
			executorCompletionService.take();
		}
		singleExecutor.execute(() -> log.info("After the main thread finishes"));

	}

}

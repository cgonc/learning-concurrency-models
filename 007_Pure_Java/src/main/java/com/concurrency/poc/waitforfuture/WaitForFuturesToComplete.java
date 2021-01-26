package com.concurrency.poc.waitforfuture;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class WaitForFuturesToComplete {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

		//4 tasks
		for(int i = 0; i < 40; i++){
			int finalI = i;
			completionService.submit(() -> {
				log.info("A task is running {}", finalI);
				Thread.sleep(300);
				return Integer.parseInt(RandomStringUtils.randomNumeric(6, 6));
			});
		}

		int received = 0;
		boolean errors = false;

		Integer sum = 0;
		while(received < 40 && !errors){
			Future<Integer> resultFuture = null; //blocks if none available
			try{
				resultFuture = completionService.take();
				log.info("A task reseult is ready");
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			try{
				assert resultFuture != null;
				Integer result = resultFuture.get();
				sum += result;
				received++;

			} catch (Exception e){
				errors = true;
			}
		}

		log.info("The result : {}", sum);
		executor.shutdown();
	}
}

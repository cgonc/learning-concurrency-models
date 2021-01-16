package com.concurrency.poc.threadcreation;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutorLab {

	private final List<Runnable> tasks;

	/*
	 * @param tasks to executed concurrently
	 */
	public MultiExecutorLab(List<Runnable> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Starts and executes all the tasks concurrently
	 */
	public void executeAll() {
		List<Thread> threads = new ArrayList<>(tasks.size());

		for(Runnable task : tasks){
			Thread thread = new Thread(task);
			threads.add(thread);
		}

		for(Thread thread : threads){
			thread.start();
		}
	}
}

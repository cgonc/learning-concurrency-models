package com.concurrency.poc.threadcreation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MainBasics04 {

	public static void main(String[] args) {
		List<Runnable> tasks = new ArrayList<>();
		tasks.add(() -> log.info("001"));
		tasks.add(() -> log.info("002"));
		tasks.add(() -> log.info("003"));
		tasks.add(() -> log.info("004"));
		tasks.add(() -> log.info("005"));

		MultiExecutorLab multiExecutorLab = new MultiExecutorLab(tasks);
		multiExecutorLab.executeAll();
	}

}

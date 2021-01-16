package com.concurrency.poc.threadcreation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings ("DuplicatedCode")
@Slf4j
public class MainBasics03 {

	public static final int MAX_PASSWORD = 10000;

	public static void main(String[] args) {
		Random random = new Random();
		Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

		List<Thread> threads = new ArrayList<>();

		threads.add(new AscendingHackerThread(vault));
		threads.add(new DescendingHackerThread(vault));
		threads.add(new PoliceThread());

		for(Thread thread : threads){
			thread.start();
		}
	}

	private static class Vault {

		private final int password;

		public Vault(int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(int guess) {
			try{
				Thread.sleep(5);
			} catch (InterruptedException ignored){
			}
			return this.password == guess;
		}
	}

	private static abstract class HackerThread extends Thread {

		protected Vault vault;

		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(Thread.MAX_PRIORITY);
		}

		@Override
		public void start() {
			log.info("Starting thread " + this.getName());
			super.start();
		}
	}

	private static class AscendingHackerThread extends HackerThread {

		public AscendingHackerThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for(int guess = 0; guess < MAX_PASSWORD; guess++){
				if(vault.isCorrectPassword(guess)){
					log.info(this.getName() + " guessed the password " + guess);
					System.exit(0);
				}
			}
		}
	}

	private static class DescendingHackerThread extends HackerThread {

		public DescendingHackerThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for(int guess = MAX_PASSWORD; guess >= 0; guess--){
				if(vault.isCorrectPassword(guess)){
					log.info(this.getName() + " guessed the password " + guess);
					System.exit(0);
				}
			}
		}
	}

	private static class PoliceThread extends Thread {

		@Override
		public void run() {
			for(int i = 10; i > 0; i--){
				try{
					Thread.sleep(1000);
				} catch (InterruptedException ignored){
				}
				log.info(i + "");
			}

			log.info("Game over for you hackers");
			System.exit(0);
		}
	}
}

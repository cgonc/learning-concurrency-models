package com.concurrency.poc.threadinterrrupted;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@SuppressWarnings ("DanglingJavadoc")
@Slf4j
/**
 * How to properly use interruption on threads.
 */
public class Main002 {

	public static void main(String[] args) {
		Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("100000000")));

		//thread.setDaemon(true);
		thread.start();
		thread.interrupt();
	}

	private static class LongComputationTask implements Runnable {

		private final BigInteger base;
		private final BigInteger power;

		public LongComputationTask(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}

		@Override
		public void run() {
			log.info(base + "^" + power + " = " + pow(base, power));
		}

		private BigInteger pow(BigInteger base, BigInteger power) {
			BigInteger result = BigInteger.ONE;

			for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)){
				if(Thread.currentThread().isInterrupted()){
					log.info("Prematurely interrupted computation");
					return BigInteger.ZERO;
				}
				result = result.multiply(base);
			}

			return result;
		}
	}

}

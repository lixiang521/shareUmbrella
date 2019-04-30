package com.pro.umbrella.common.metrics.metric.impl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author sen.chai on 2017/5/19
 */
public class SimpleRater {

	private AtomicLong counter = new AtomicLong();

	private volatile long lastValue = 0;

	private volatile long lastTime = getTime();

	private long getTime() {
		return System.currentTimeMillis();
	}

	public void mark(long count) {
		counter.addAndGet(count);
	}

	public void mark() {
		mark(1);
	}

	public double getValue() {
		long last = lastValue;
		long lastT = lastTime;
		lastTime = getTime();
		lastValue = counter.get();
		double delta = lastValue - last;
		if (delta < 0) {
			delta = 0;
		}
		long duration = lastTime - lastT;
		if (duration == 0) {
			return 0;
		}
		return delta * 1.0 / (duration / 1000.0);
	}

	public void reset() {
		//empty
	}
}

package com.pro.umbrella.common.metrics.metric.impl;

import java.util.concurrent.atomic.AtomicLong;

import com.pro.umbrella.common.metrics.config.MetricConfig;

/**
 * @author sen.chai on 2017/5/19
 */
public class DefaultCounter extends AbstractCounter {

	private AtomicLong valueHolder = new AtomicLong(0);

	DefaultCounter(MetricConfig metricConfig, boolean reset) {
		super(metricConfig, reset);
	}

	@Override
	public void inc(long count) {
		valueHolder.addAndGet(count);
	}

	@Override
	public void dec(long count) {
		valueHolder.addAndGet(-count);
	}

	@Override
	public Long getValue() {
		if (isReset()) {
			return valueHolder.getAndSet(0);
		}
		else {
			return valueHolder.get();
		}
	}

	@Override
	public Long peekValue() {
		return valueHolder.get();
	}
}

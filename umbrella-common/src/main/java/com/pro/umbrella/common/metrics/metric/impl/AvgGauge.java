package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;

/**
 * @author sen.chai on 2017/5/19
 */
public class AvgGauge extends AbstractResettableGauge {

	private volatile double sum;

	private volatile long times;

	AvgGauge(MetricConfig metricConfig, boolean reset) {
		super(metricConfig, reset);
	}

	@Override
	public Double getValue() {
		try {
			if (times == 0) {
				return 0.0;
			}
			return sum / times;
		}
		finally {
			if (isReset()) {
				doReset();
			}
		}
	}

	@Override
	public Double peekValue() {
		if (times == 0) {
			return 0.0;
		}
		return sum / times;
	}

	private synchronized void doReset() {
		sum = 0;
		times = 0;
	}

	@Override
	public synchronized void update(double value) {
		times++;
		sum += value;
	}
}

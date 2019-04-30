package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;

/**
 * @author sen.chai on 2017/5/19
 */
public class MinGauge extends DefaultResettableGauge {

	MinGauge(MetricConfig metricConfig, boolean reset) {
		super(metricConfig, reset, Integer.MAX_VALUE);
	}

	@Override
	public void update(double value) {
		if (justGetValue() > value) {
			super.update(value);
		}
	}
}

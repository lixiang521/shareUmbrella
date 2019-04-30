package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;

/**
 * @author sen.chai on 2017/5/19
 */
public class MaxGauge extends DefaultResettableGauge {

	MaxGauge(MetricConfig metricConfig, boolean reset) {
		super(metricConfig, reset, Integer.MIN_VALUE);
	}

	@Override
	public void update(double value) {
		if (justGetValue() < value) {
			super.update(value);
		}
	}
}

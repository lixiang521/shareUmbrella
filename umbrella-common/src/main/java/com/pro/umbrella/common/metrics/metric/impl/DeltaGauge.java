package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;

/**
 * @author sen.chai on 2017/5/25
 */
public class DeltaGauge extends DefaultGauge {

	private volatile double lastValue;

	private volatile boolean init;

	DeltaGauge(MetricConfig metricConfig, GaugeComputer callable) {
		super(metricConfig, callable);
	}

	@Override
	public Double getValue() {
		Double value = super.getValue();
		double result;
		if (init) {
			result = value - lastValue;
		}
		else {
			result = 0;
			init = true;
		}
		lastValue = value;
		return result;
	}
}

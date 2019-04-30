package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;

/**
 * @author sen.chai on 2017/5/19
 */
public class DefaultGauge extends AbstractGauge {

	private final GaugeComputer callable;

	DefaultGauge(MetricConfig metricConfig, GaugeComputer callable) {
		super(metricConfig);
		this.callable = callable;
	}

	@Override
	public Double getValue() {
		try {
			return callable.compute();
		}
		catch (Throwable e) {
			return 0.0;
		}
	}

	@Override
	public Double peekValue() {
		return getValue();
	}
}

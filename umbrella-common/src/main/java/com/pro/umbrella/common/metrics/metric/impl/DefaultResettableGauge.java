package com.pro.umbrella.common.metrics.metric.impl;

import com.google.common.util.concurrent.AtomicDouble;
import com.pro.umbrella.common.metrics.config.MetricConfig;

/**
 * @author sen.chai on 2017/5/19
 */
public class DefaultResettableGauge extends AbstractResettableGauge {

	private AtomicDouble valueHolder;

	private double initValue;

	DefaultResettableGauge(MetricConfig metricConfig, boolean reset, double initValue) {
		super(metricConfig, reset);
		this.initValue = initValue;
		valueHolder = new AtomicDouble(initValue);
	}

	DefaultResettableGauge(MetricConfig metricConfig, boolean reset) {
		this(metricConfig, reset, 0);
	}

	@Override
	public void update(double value) {
		valueHolder.set(value);
	}

	@Override
	public Double getValue() {
		if (isReset()) {
			return getReasonableValue(valueHolder.getAndSet(initValue));
		}
		else {
			return getUnResetValue();
		}
	}

	private Double getUnResetValue() {
		double value = justGetValue();
		return getReasonableValue(value);
	}

	@Override
	public Double peekValue() {
		return getUnResetValue();
	}

	private Double getReasonableValue(double value) {
		if (value == initValue) {
			return 0.0;
		}
		else {
			return value;
		}
	}

	protected double justGetValue() {
		return valueHolder.get();
	}
}

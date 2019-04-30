package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.Counter;
import com.pro.umbrella.common.metrics.metric.MetricType;
import com.pro.umbrella.common.metrics.metric.Resettable;
import com.pro.umbrella.common.metrics.metric.ValueType;

/**
 * @author sen.chai on 2017/5/19
 */
public abstract class AbstractCounter extends AbstractMetricItem<Long> implements Counter, Resettable {

	private final boolean reset;

	AbstractCounter(MetricConfig metricConfig, boolean reset) {
		super(metricConfig);
		this.reset = reset;
	}

	@Override
	public void inc() {
		inc(1);
	}

	@Override
	public void dec() {
		dec(1);
	}

	@Override
	public MetricType getType() {
		return MetricType.COUNTER;
	}

	@Override
	public boolean isReset() {
		return reset;
	}

	@Override
	public double resolveValue(Long value, ValueType valueType) {
		return value.doubleValue();
	}
}

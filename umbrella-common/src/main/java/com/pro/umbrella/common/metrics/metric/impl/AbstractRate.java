package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.MetricType;
import com.pro.umbrella.common.metrics.metric.Rate;
import com.pro.umbrella.common.metrics.metric.ValueType;

/**
 * @author sen.chai on 2017/5/19
 */
public abstract class AbstractRate extends AbstractMetricItem<Double> implements Rate {

	AbstractRate(MetricConfig metricConfig) {
		super(metricConfig);
	}

	@Override
	public MetricType getType() {
		return MetricType.RATE;
	}

	@Override
	public double resolveValue(Double value, ValueType valueType) {
		return value;
	}
}

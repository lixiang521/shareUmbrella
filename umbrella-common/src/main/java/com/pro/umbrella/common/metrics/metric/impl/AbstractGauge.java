package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.Gauge;
import com.pro.umbrella.common.metrics.metric.MetricType;
import com.pro.umbrella.common.metrics.metric.ValueType;

/**
 * @author sen.chai on 2017/5/19
 */
public abstract class AbstractGauge extends AbstractMetricItem<Double> implements Gauge {

	AbstractGauge(MetricConfig metricConfig) {
		super(metricConfig);
	}

	@Override
	public MetricType getType() {
		return MetricType.GAUGE;
	}

	@Override
	public double resolveValue(Double value, ValueType valueType) {
		return value;
	}
}

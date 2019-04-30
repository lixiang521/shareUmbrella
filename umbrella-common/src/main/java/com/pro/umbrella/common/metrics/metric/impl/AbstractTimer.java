package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.MetricType;
import com.pro.umbrella.common.metrics.metric.TimeValue;
import com.pro.umbrella.common.metrics.metric.Timer;
import com.pro.umbrella.common.metrics.metric.ValueType;

/**
 * @author sen.chai on 2017/5/19
 */
public abstract class AbstractTimer extends AbstractMetricItem<TimeValue> implements Timer {

	AbstractTimer(MetricConfig metricConfig) {
		super(metricConfig);
	}

	@Override
	public MetricType getType() {
		return MetricType.TIMER;
	}

	@Override
	public double resolveValue(TimeValue timeValue, ValueType valueType) {
		return timeValue.resolveValue(valueType);
	}
}

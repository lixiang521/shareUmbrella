package com.pro.umbrella.common.metrics.metric;

import com.pro.umbrella.common.metrics.config.MetricConfig;

/**
 * @author sen.chai on 2017/5/19
 */
public interface MetricItem<T> {

	MetricConfig getMetricConfig();

	T getValue();

	T peekValue();

	MetricType getType();

	double resolveValue(T value, ValueType valueType);
}

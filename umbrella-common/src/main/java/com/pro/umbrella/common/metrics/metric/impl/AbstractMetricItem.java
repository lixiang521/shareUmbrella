package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.MetricItem;

/**
 * @author sen.chai on 2017/5/19
 */
public abstract class AbstractMetricItem<T> implements MetricItem<T> {

	private MetricConfig metricConfig;

	AbstractMetricItem(MetricConfig metricConfig) {
		this.metricConfig = metricConfig;
	}

	@Override
	public MetricConfig getMetricConfig() {
		return metricConfig;
	}
}

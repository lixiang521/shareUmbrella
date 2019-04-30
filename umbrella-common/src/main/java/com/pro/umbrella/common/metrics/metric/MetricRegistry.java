package com.pro.umbrella.common.metrics.metric;

import com.pro.umbrella.common.metrics.config.MetricConfig;

/**
 * @author sen.chai on 2017/5/19
 */
public interface MetricRegistry extends Iterable<MetricItem> {

	MetricItem get(MetricConfig metricConfig);

	void register(MetricItem metricItem);

	int size();
}

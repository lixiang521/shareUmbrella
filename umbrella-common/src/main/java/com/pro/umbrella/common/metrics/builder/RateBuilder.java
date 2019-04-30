package com.pro.umbrella.common.metrics.builder;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.Rate;
import com.pro.umbrella.common.metrics.metric.impl.MetricsFactory;

/**
 * @author sen.chai on 2017/5/21.
 */
public class RateBuilder extends AbstractBuilder<Rate> {

	public RateBuilder(String metricName) {
		super(metricName);
	}

	public RateBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	@Override
	protected Rate buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newDefaultRate(metricConfig);
	}
}

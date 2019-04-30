package com.pro.umbrella.common.metrics.builder;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.Timer;
import com.pro.umbrella.common.metrics.metric.impl.MetricsFactory;

/**
 * @author sen.chai on 2017/5/21.
 */
public class TimerBuilder extends AbstractBuilder<Timer> {

	public TimerBuilder(String metricName) {
		super(metricName);
	}

	public TimerBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	@Override
	protected Timer buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newDefaultTimer(metricConfig);
	}
}

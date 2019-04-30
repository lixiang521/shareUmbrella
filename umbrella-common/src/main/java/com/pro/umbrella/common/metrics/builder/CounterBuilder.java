package com.pro.umbrella.common.metrics.builder;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.Counter;
import com.pro.umbrella.common.metrics.metric.impl.MetricsFactory;

/**
 * @author sen.chai on 2017/5/21.
 */
public class CounterBuilder extends AbstractResetBuilder<Counter> {

	public CounterBuilder(String metricName) {
		super(metricName, true);//默认清零
	}

	public CounterBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	public CounterBuilder reset(boolean reset) {
		withReset(reset);
		return this;
	}

	public CounterBuilder delta() {
		withReset(true);
		return this;
	}

	@Override
	protected Counter buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newCounter(metricConfig, isReset());
	}
}

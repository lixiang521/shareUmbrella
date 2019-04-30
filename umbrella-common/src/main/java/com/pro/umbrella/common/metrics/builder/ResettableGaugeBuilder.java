package com.pro.umbrella.common.metrics.builder;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.ResettableGauge;
import com.pro.umbrella.common.metrics.metric.impl.MetricsFactory;

/**
 * @author sen.chai on 2017/5/21.
 */
public class ResettableGaugeBuilder extends AbstractResetBuilder<ResettableGauge> {

	public ResettableGaugeBuilder(String metricName) {
		super(metricName, true);
	}

	public ResettableGaugeBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	public ResettableGaugeBuilder reset(boolean reset) {
		withReset(reset);
		return this;
	}

	public ResettableGaugeBuilder delta() {
		withReset(true);
		return this;
	}

	@Override
	protected ResettableGauge buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newResettableGauge(metricConfig, isReset());
	}

	public ResettableGauge getMax() {
		return MetricsFactory.newMaxGauge(createMetricConfig(), isReset());
	}

	public ResettableGauge getMin() {
		return MetricsFactory.newMinGauge(createMetricConfig(), isReset());
	}

	public ResettableGauge getAvg() {
		return MetricsFactory.newAvgGauge(createMetricConfig(), isReset());
	}
}

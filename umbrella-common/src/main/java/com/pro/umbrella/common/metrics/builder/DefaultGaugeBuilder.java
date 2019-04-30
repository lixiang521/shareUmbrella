package com.pro.umbrella.common.metrics.builder;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.Gauge;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;
import com.pro.umbrella.common.metrics.metric.impl.DeltaGauge;
import com.pro.umbrella.common.metrics.metric.impl.MetricsFactory;

/**
 * @author sen.chai on 2017/5/21.
 */
public class DefaultGaugeBuilder extends AbstractBuilder<Gauge> {

	private final GaugeComputer gaugeComputer;

	public DefaultGaugeBuilder(String metricName, GaugeComputer gaugeComputer) {
		super(metricName);
		this.gaugeComputer = gaugeComputer;
	}

	public DefaultGaugeBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	@Override
	protected Gauge buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newDefaultGauge(metricConfig, gaugeComputer);
	}

	public DeltaGauge getDelta() {
		return MetricsFactory.newDeltaGauge(createMetricConfig(), gaugeComputer);
	}
}

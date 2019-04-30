package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.ResettableGauge;

/**
 * @author sen.chai on 2017/5/25
 */
public abstract class AbstractResettableGauge extends AbstractGauge implements ResettableGauge {

	private boolean reset;

	AbstractResettableGauge(MetricConfig metricConfig, boolean reset) {
		super(metricConfig);
		this.reset = reset;
	}

	@Override
	public boolean isReset() {
		return reset;
	}
}
